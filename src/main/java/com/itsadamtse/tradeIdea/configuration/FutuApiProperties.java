package com.itsadamtse.tradeIdea.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "futu.api")
public class FutuApiProperties {
    private String baseUrl;
    private String apiKey;
    private String snapshotEndpoint;
    private List<String> symbols;
    private long fetchIntervalMs;
}
