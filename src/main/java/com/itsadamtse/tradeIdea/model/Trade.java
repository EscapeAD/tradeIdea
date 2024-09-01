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
    private String symbol;
    private double price;
    private int quantity;
    private Instant timestamp;

    // Constructors, getters, and setters are handled by Lombok's @Data
}
