package com.itsadamtse.tradeIdea.controller;

import com.itsadamtse.tradeIdea.model.Trade;
import com.itsadamtse.tradeIdea.service.TradeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public Mono<Trade> createTrade(@RequestBody Trade trade) {
        return tradeService.saveTrade(trade);
    }

    @GetMapping
    public Flux<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }

    @GetMapping("/{symbol}")
    public Flux<Trade> getTradesBySymbol(@PathVariable String symbol) {
        return tradeService.getTradesBySymbol(symbol);
    }
}