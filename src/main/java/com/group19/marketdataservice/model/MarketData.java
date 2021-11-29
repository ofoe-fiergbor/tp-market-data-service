package com.group19.marketdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketData {

    private Double lastTradedPrice;
    private Integer sellLimit;
    private Double bidPrice;
    private Integer askPrice;
    private Integer buyLimit;
    private String ticker;
    private Integer maxPriceShift;
}
