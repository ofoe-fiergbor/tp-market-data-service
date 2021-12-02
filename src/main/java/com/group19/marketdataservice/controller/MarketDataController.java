package com.group19.marketdataservice.controller;

import com.group19.marketdataservice.MarketDataServiceApplication;
import com.group19.marketdataservice.model.MarketData;
import com.group19.marketdataservice.service.MarketDataService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/exchange")
public class MarketDataController {

    private final Logger logger = LoggerFactory.getLogger(MarketDataServiceApplication.class);

    @Autowired
    MarketDataService marketDataService;
    @Autowired
    RestTemplate restTemplate;


    @Cacheable("allMarketData")
    @GetMapping("/md")
    public List<MarketData> getAllMarketData(){
        logger.info("Fetching Market Data from both exchanges");
        return marketDataService.allMarketData();
    }

    @Cacheable("bothMarketProductType")
    @GetMapping("/{exchange}/md/{productType}")
    public MarketData getMarketData3(@PathVariable("productType") String productType, @PathVariable("exchange") String exchange){
        if (exchange.equals("1")){
            logger.info("Fetching specific data from exchange one");
            return marketDataService.exchangeOneMarketDataProductType(productType);
        }else {
            logger.info("Fetching specific data from exchange two");
            return marketDataService.exchangeTwoMarketDataProductType(productType);
        }
    }

    @Cacheable("bothMarketProduct")
    @GetMapping("/{exchange}/md")
    public List<MarketData> getMarketData1(@PathVariable String exchange){

        if (exchange.equals("1")){
            logger.info("Fetching all data from exchange one");
            return marketDataService.exchangeOneMarketData();
        }else {
            logger.info("Fetching all data from exchange two");
            return marketDataService.exchangeTwoMarketData();
        }
    }


}
