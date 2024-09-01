package com.itsadamtse.tradeIdea.service;

import com.itsadamtse.tradeIdea.model.FutuApiResponse;
import com.itsadamtse.tradeIdea.model.Trade;
import com.itsadamtse.tradeIdea.repository.TradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.Instant;

@Service
public class MarketDataService {

    private final TradeRepository tradeRepository;
    private final WebClient webClient;
    private final String apiKey;

    public MarketDataService(TradeRepository tradeRepository,
                             WebClient.Builder webClientBuilder,
                             @Value("${futu.api.base-url}") String baseUrl,
                             @Value("${futu.api.key}") String apiKey) {
        this.tradeRepository = tradeRepository;
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    public Flux<Trade> streamMarketData(String security) {
        return Flux.interval(Duration.ofSeconds(5))
                .flatMap(i -> fetchMarketSnapshot(security))
                .flatMap(this::saveTrade);
    }

    private Mono<Trade> fetchMarketSnapshot(String security) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/futu-api/market-snapshot")
                        .queryParam("security", security)
                        .build())
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToMono(FutuApiResponse.class)
                .map(this::convertToTrade)
                .onErrorResume(e -> {
                    // Log error using a proper logging framework
                    return Mono.empty();
                });
    }

    private Trade convertToTrade(FutuApiResponse response) {
        Schedulers.Snapshot snapshot = response.getS2c().getSnapshotList().get(0);
        SnapshotBasicData basicData = snapshot.getBasic();

        Trade trade = new Trade();
        trade.setSecurity(basicData.getSecurity().getMarket() + "." + basicData.getSecurity().getCode());
        trade.setName(basicData.getName());
        trade.setLastPrice(basicData.getCurPrice());
        trade.setVolume(basicData.getVolume());
        trade.setTurnover(basicData.getTurnover());
        trade.setOpenPrice(basicData.getOpenPrice());
        trade.setHighPrice(basicData.getHighPrice());
        trade.setLowPrice(basicData.getLowPrice());
        trade.setUpdateTime(Instant.ofEpochSecond(basicData.getUpdateTimestamp()));

        // Handle additional data based on security type
        switch (basicData.getType()) {
            case 1: // Equity
                if (snapshot.getEquityExData() != null) {
                    trade.setIssuedShares(snapshot.getEquityExData().getIssuedShares());
                    trade.setPeRatio(snapshot.getEquityExData().getPeRate());
                }
                break;
            case 2: // Index
                if (snapshot.getIndexExData() != null) {
                    trade.setRaiseCount(snapshot.getIndexExData().getRaiseCount());
                    trade.setFallCount(snapshot.getIndexExData().getFallCount());
                }
                break;
            // Add cases for other security types as needed
        }

        return trade;
    }

    private Mono<Trade> saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }
}