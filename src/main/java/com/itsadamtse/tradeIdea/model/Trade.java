package com.itsadamtse.tradeIdea.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.Instant;

@Data
@Document(collection = "trades")
public class Trade {
    @Id
    private String id;
    private String symbol;  // Add this field
    private String name;
    private double lastPrice;
    private long volume;
    private double turnover;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private Instant updateTime;
    private long issuedShares;
    private double peRatio;
    private int raiseCount;
    private int fallCount;
    // Constructors, getters, and setters are handled by Lombok's @Data
}