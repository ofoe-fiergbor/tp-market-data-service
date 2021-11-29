package com.group19.marketdataservice.controller;

import com.group19.marketdataservice.model.MarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class MarketDataController {

//    @Autowired
//    MarketDataService1 marketDataService;
//
//    @GetMapping("/hello")
//    public String sayHi() {
//        return marketDataService.sayHi();
//    }
//
//    @GetMapping("/md")
//    public List<MarketData> getMarketData(){
//        return marketDataService.getMarketData();
//    }
}
