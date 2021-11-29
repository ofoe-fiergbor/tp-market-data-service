package com.group19.marketdataservice.service;

import com.group19.marketdataservice.model.MarketData;
import com.group19.marketdataservice.repository.MarketDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketDataService {
    private static final String GATEWAY_ENDPOINT = "https://exchange.matraining.com";
    private static final String DATA_CONTEXT = "/md";
    private static final String DATA_GET_BY_STOCK_NAME = "{stock_name}";

    private final RestTemplate restTemplate;
    private final MarketDataRepository marketDataRepository;

    public Optional<MarketData> getMarketDataById(int id){
        MarketData cacheMarketData = marketDataRepository.findMarketDataById(id);

        if (nonNull(cacheMarketData)){
           return Optional.of(cacheMarketData);
        }

        ResponseEntity<MarketData> marketData = restTemplate.exchange(DATA_GET_BY_STOCK_NAME,GET,null, MarketData.class,id);

        if ((nonNull(marketData.getBody()))){
            cacheMarketData(marketData.getBody());
        }

        return Optional.ofNullable(marketData.getBody());
    }

    private void cacheMarketData(MarketData body) {

        try{
            marketDataRepository.
        }
    }

}
