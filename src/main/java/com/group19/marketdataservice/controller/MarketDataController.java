package com.group19.marketdataservice.controller;

import com.group19.marketdataservice.domain.model.StockProduct;
import com.group19.marketdataservice.domain.repository.ExchangeRepository;
import com.group19.marketdataservice.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market-data")
public class MarketDataController {

    @Autowired
    MarketDataService marketDataService;
    @Autowired
    private ExchangeRepository exchangeRepository;


    @PostMapping("/exchange-one")
    public ResponseEntity<?> fetchMarketDataFromExchangeOne() {
        return new ResponseEntity<>( marketDataService
                .fetchStockProducts(exchangeRepository.getById(1L).getExchangeURL() + "/subscription",
                        "https://localhost:8086/market-data/exchange-one/callback"),HttpStatus.OK);
    }


    @PostMapping("/exchange-one/callback")
    public ResponseEntity<List<StockProduct>> callbackOne(@RequestBody List<StockProduct> stockProductList) {
       return new ResponseEntity<>(stockProductList, HttpStatus.OK);
    }

    @DeleteMapping("/exchange-one")
    public ResponseEntity<?> unsubscribeFromExchangeOne(@RequestBody String callbackUrl) {
        return new ResponseEntity<>( marketDataService
                .fetchStockProducts(exchangeRepository.getById(1L).getExchangeURL() + "/subscription",
                        callbackUrl),HttpStatus.OK);
    }
    @PostMapping("/exchange-two")
    public ResponseEntity<?> fetchMarketDataFromExchangeTwo(@RequestBody String callbackUrl) {
        return new ResponseEntity<>( marketDataService
                .fetchStockProducts(exchangeRepository.getById(2L).getExchangeURL() + "/subscription", callbackUrl), HttpStatus.OK);
    }


    @PostMapping("/exchange-two/callback")
    public ResponseEntity<List<StockProduct>> callbackTwo(@RequestBody List<StockProduct> stockProductList) {
        return new ResponseEntity<>(stockProductList, HttpStatus.OK);
    }

    @DeleteMapping("/exchange-two")
    public ResponseEntity<?> unsubscribeFromExchangeTwo(@RequestBody String callbackUrl) {
        return new ResponseEntity<>( marketDataService
                .fetchStockProducts(exchangeRepository.getById(2L)
                        .getExchangeURL() + "/subscription",
                        "https://localhost:8086/market-data/exchange-two/callback"), HttpStatus.OK);
    }

}
