package com.itsadamtse.tradeIdea.model;

import lombok.Data;

@Data
public class Snapshot {
    private SnapshotBasicData basic;
    private EquitySnapshotExData equityExData;
    private IndexSnapshotExData indexExData;
    // Add other specific data types as needed
    // getters and setters
}
