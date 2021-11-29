package com.group19.marketdataservice.repository;

import com.group19.marketdataservice.model.MarketData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MarketDataRepositoryImpl implements MarketDataRepository {
    private static  final String REDIS_ENTITY = "MARKET_DATA";
    private final RedisTemplate<String,MarketData> redisTemplate;
    private HashOperations<String, Integer, MarketData> hashOperations;

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public MarketData findMarketDataById(int marketDataId) {
        return hashOperations.get(REDIS_ENTITY,marketDataId);
    }

    @Override
    public void deleteMarketDataById(int marketDataId) {
        hashOperations.delete(REDIS_ENTITY,marketDataId);

    }

    @Override
    public List<MarketData> allMarketData() {
        return null;
    }
    @Override
    void saveMarketData(MarketData marketData){}

}
