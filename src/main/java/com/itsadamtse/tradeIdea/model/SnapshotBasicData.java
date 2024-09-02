package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class SnapshotBasicData {
    private Security security;
    private String name;
    private int type;
    private boolean isSuspend;
    private String listTime;
    private int lotSize;
    private double priceSpread;
    private String updateTime;
    private double highPrice;
    private double openPrice;
    private double lowPrice;
    private double lastClosePrice;
    private double curPrice;
    private long volume;
    private double turnover;
    private double turnoverRate;
    private Long listTimestamp;
    private Long updateTimestamp;
    private Double askPrice;
    private Double bidPrice;
    private Long askVol;
    private Long bidVol;
    // Add other fields as needed
    // getters and setters
}
