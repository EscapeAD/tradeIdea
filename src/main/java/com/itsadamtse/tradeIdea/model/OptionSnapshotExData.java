package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class OptionSnapshotExData {
    private int type;
    private Security owner;
    private String strikeTime;
    private double strikePrice;
    private int contractSize;
    private Double contractSizeFloat;
    private int openInterest;
    private double impliedVolatility;
    private double premium;
    private double delta;
    private double gamma;
    private double vega;
    private double theta;
    private double rho;
    private Long strikeTimestamp;
    private Integer indexOptionType;
    private Integer netOpenInterest;
    private Integer expiryDateDistance;
    private Double contractNominalValue;
    private Double ownerLotMultiplier;
    private Integer optionAreaType;
    private Double contractMultiplier;
}
