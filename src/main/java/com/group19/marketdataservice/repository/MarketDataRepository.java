package com.group19.marketdataservice.repository;

import com.group19.marketdataservice.model.MarketData;

import java.util.List;

public interface MarketDataRepository {
    MarketData findMarketDataById(int marketDataId);
    void deleteMarketDataById(int marketDataId);
    List<MarketData> allMarketData();
    void saveMarketData(MarketData marketData);
}
