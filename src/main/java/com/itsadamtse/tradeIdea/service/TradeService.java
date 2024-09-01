package com.itsadamtse.tradeIdea.service;

import com.itsadamtse.tradeIdea.model.Trade;
import com.itsadamtse.tradeIdea.repository.TradeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Mono<Trade> saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Flux<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Flux<Trade> getTradesBySymbol(String symbol) {
        return tradeRepository.findBySymbol(symbol);
    }
}