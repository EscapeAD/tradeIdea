package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class Snapshot {
    private SnapshotBasicData basic;
    private EquitySnapshotExData equityExData;
    private WarrantSnapshotExData warrantExData;
    private OptionSnapshotExData optionExData;
    private IndexSnapshotExData indexExData;
    private PlateSnapshotExData plateExData;
    private FutureSnapshotExData futureExData;
    private TrustSnapshotExData trustExData;
    // Add other specific data types as needed
    // getters and setters
}