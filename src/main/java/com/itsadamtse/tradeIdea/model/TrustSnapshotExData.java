package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class TrustSnapshotExData {
    private double dividendYield;
    private double aum;
    private long outstandingUnits;
    private double netAssetValue;
    private double premium;
    private int assetClass;
}
