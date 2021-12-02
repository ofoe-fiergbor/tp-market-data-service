package com.group19.marketdataservice.service;

import com.group19.marketdataservice.model.MarketData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class MarketDataService {
    private  String EXCHANGE_ONE_URL = "https://exchange.matraining.com/md/";
    private  String EXCHANGE_TWO_URL = "https://exchange2.matraining.com/md/";

    private final RestTemplate restTemplate;

    public List<MarketData> exchangeOneMarketData(){
        List<MarketData> marketData = restTemplate.getForObject(EXCHANGE_ONE_URL,List.class);
        return marketData;
    }

    public List<MarketData> exchangeTwoMarketData(){
        List<MarketData> marketData = restTemplate.getForObject(EXCHANGE_TWO_URL,List.class);
        return marketData;
    }

    public MarketData exchangeTwoMarketDataProductType(String productType){
        String url = EXCHANGE_TWO_URL+productType;
        MarketData marketData = restTemplate.getForObject("https://exchange2.matraining.com/md/"+productType,MarketData.class);
        return marketData;
    }

    public MarketData exchangeOneMarketDataProductType(String productType){
        String url = EXCHANGE_ONE_URL+productType;
        MarketData marketData = restTemplate.getForObject("https://exchange.matraining.com/md/"+productType,MarketData.class);
        return marketData;
    }

    public List<MarketData> allMarketData(){
        List<MarketData> marketData = new ArrayList<>();
        marketData.addAll(exchangeOneMarketData());
        marketData.addAll(exchangeTwoMarketData());

        return marketData;
    }



}
