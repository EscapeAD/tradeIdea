package com.itsadamtse.tradeIdea.service;

import com.itsadamtse.tradeIdea.configuration.FutuApiProperties;
import com.itsadamtse.tradeIdea.model.FutuApiResponse;
import com.itsadamtse.tradeIdea.model.Snapshot;
import com.itsadamtse.tradeIdea.model.SnapshotBasicData;
import com.itsadamtse.tradeIdea.model.Trade;
import com.itsadamtse.tradeIdea.repository.TradeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.Instant;

@Service
public class MarketDataService {

    private final TradeRepository tradeRepository;
    private final WebClient webClient;
    private final FutuApiProperties futuApiProperties;

    public MarketDataService(TradeRepository tradeRepository,
                             WebClient.Builder webClientBuilder,
                             FutuApiProperties futuApiProperties) {
        this.tradeRepository = tradeRepository;
        this.futuApiProperties = futuApiProperties;
        this.webClient = webClientBuilder
                .baseUrl(futuApiProperties.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + futuApiProperties.getApiKey())
                .build();
    }

    @Scheduled(fixedRateString = "${futu.api.fetch-interval-ms}")
    public void fetchAndStoreMarketData() {
        Flux.fromIterable(futuApiProperties.getSymbols())
                .flatMap(this::fetchMarketSnapshot)
                .flatMap(this::saveTrade)
                .subscribe(
                        trade -> System.out.println("Saved trade for " + trade.getSymbol()),
                        error -> System.err.println("Error fetching market data: " + error.getMessage())
                );
    }

    private Mono<Trade> fetchMarketSnapshot(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(futuApiProperties.getSnapshotEndpoint())
                        .queryParam("security", symbol)
                        .build())
                .retrieve()
                .bodyToMono(FutuApiResponse.class)
                .map(response -> convertToTrade(response, symbol));
    }

    private Trade convertToTrade(FutuApiResponse response, String symbol) {
        // Assuming the response contains the data we need
        SnapshotBasicData basicData = response.getS2c().getSnapshotList().get(0).getBasic();

        Trade trade = new Trade();
        trade.setSymbol(symbol);
        trade.setName(basicData.getName());
        trade.setLastPrice(basicData.getCurPrice());
        trade.setVolume(basicData.getVolume());
        trade.setTurnover(basicData.getTurnover());
        trade.setOpenPrice(basicData.getOpenPrice());
        trade.setHighPrice(basicData.getHighPrice());
        trade.setLowPrice(basicData.getLowPrice());
        trade.setUpdateTime(Instant.ofEpochSecond(basicData.getUpdateTimestamp()));

        // Add more fields as necessary

        return trade;
    }

    private Mono<Trade> saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

   // market Data Trades
    public Flux<Trade> getTradesBySymbol(String symbol) {
        return tradeRepository.findBySymbol(symbol);
    }

    public Mono<Trade> getLatestTrade(String symbol) {
        return tradeRepository.findBySymbol(symbol)
                .sort((t1, t2) -> t2.getUpdateTime().compareTo(t1.getUpdateTime()))
                .next();
    }
}