package com.itsadamtse.tradeIdea.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "futu.api")
public class FutuApiProperties {
    private String baseUrl;
    private String apiKey;
    private String snapshotEndpoint;
    private int pollingIntervalSeconds;

    // Getters and setters
}
