package com.itsadamtse.tradeIdea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class TradeDataController {

    @GetMapping("/stream-trades")
    public Flux<String> streamTrades() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Trade " + sequence);
    }
}