package com.itsadamtse.tradeIdea.repository;

import com.itsadamtse.tradeIdea.model.Trade;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TradeRepository extends ReactiveMongoRepository<Trade, String> {
    Flux<Trade> findBySymbol(String symbol);
}
