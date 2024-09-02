package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class WarrantSnapshotExData {
    private double conversionRate;
    private int warrantType;
    private double strikePrice;
    private String maturityTime;
    private String endTradeTime;
    private Security owner;
    private double recoveryPrice;
    private long streetVolumn;
    private long issueVolumn;
    private double streetRate;
    private double delta;
    private double impliedVolatility;
    private double premium;
    private Double maturityTimestamp;
    private Double endTradeTimestamp;
    private Double leverage;
    private Double ipop;
    private Double breakEvenPoint;
    private Double conversionPrice;
    private Double priceRecoveryRatio;
    private Double score;
    private Double upperStrikePrice;
    private Double lowerStrikePrice;
    private Integer inLinePriceStatus;
    private String issuerCode;
}
