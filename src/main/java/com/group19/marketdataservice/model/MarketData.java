package com.group19.marketdataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketData implements Serializable {

    @JsonProperty("LAST_TRADED_PRICE")
    private Double lastTradedPrice;

    @JsonProperty("SELL_LIMIT")
    private Integer sellLimit;

    @JsonProperty("BID_PRICE")
    private Double bidPrice;

    @JsonProperty("ASK_PRICE")
    private Integer askPrice;

    @JsonProperty("BUY_LIMIT")
    private Integer buyLimit;

    @JsonProperty("TICKER")
    private String ticker;

    @JsonProperty("MAX_PRICE_SHIFT")
    private Integer maxPriceShift;
}
