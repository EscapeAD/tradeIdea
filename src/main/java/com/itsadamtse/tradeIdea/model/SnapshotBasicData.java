package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class SnapshotBasicData {
    private Security security;
    private String name;
    private int type;
    private double curPrice;
    private long volume;
    private double turnover;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private long updateTimestamp;
    // Add other fields as needed
    // getters and setters
}
