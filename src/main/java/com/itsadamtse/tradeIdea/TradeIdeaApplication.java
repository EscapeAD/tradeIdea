package com.itsadamtse.tradeIdea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableReactiveMongoRepositories(basePackages = "com.itsadamtse.tradeIdea.repository")
public class TradeIdeaApplication {
	public static void main(String[] args) {
		SpringApplication.run(TradeIdeaApplication.class, args);
	}
}