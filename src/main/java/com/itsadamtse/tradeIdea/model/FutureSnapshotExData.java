package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class FutureSnapshotExData {
    private double lastSettlePrice;
    private int position;
    private int positionChange;
    private String lastTradeTime;
    private Double lastTradeTimestamp;
    private boolean isMainContract;
}
