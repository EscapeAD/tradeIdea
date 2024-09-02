package com.itsadamtse.tradeIdea.controller;

import com.itsadamtse.tradeIdea.model.Trade;
import com.itsadamtse.tradeIdea.service.MarketDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/market-data")
public class MarketDataController {

    private final MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/trades/{symbol}")
    public Flux<Trade> getTradesBySymbol(@PathVariable String symbol) {
        return marketDataService.getTradesBySymbol(symbol);
    }

    @GetMapping("/latest/{symbol}")
    public Mono<Trade> getLatestTrade(@PathVariable String symbol) {
        return marketDataService.getLatestTrade(symbol);
    }
}