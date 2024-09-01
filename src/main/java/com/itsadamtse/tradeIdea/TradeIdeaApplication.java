package com.itsadamtse.tradeIdea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeIdeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeIdeaApplication.class, args);
	}
}
