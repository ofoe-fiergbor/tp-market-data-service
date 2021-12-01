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

    @GetMapping("/fetch/exchange-one/{product}")
    public ResponseEntity<StockProduct> fetchSingleProductOne(@PathVariable("product") String product) {
        return new ResponseEntity<>(marketDataService.fetchSingleStockProduct(
                exchangeRepository.getById(1L).getExchangeURL()+"/"+product), HttpStatus.OK);
    }
    @GetMapping("/fetch/exchange-two/{product}")
    public ResponseEntity<StockProduct> fetchSingleProductTwo(@PathVariable String product) {
        return new ResponseEntity<>(marketDataService.fetchSingleStockProduct(
                exchangeRepository.getById(2L).getExchangeURL()+"/"+product), HttpStatus.OK);
    }

    @GetMapping("/fetch/exchange-one")
    public ResponseEntity<List<StockProduct>> fetchExchangeDataOne() {
        return new ResponseEntity<>(marketDataService
                .fetchStockProduct(exchangeRepository.getById(1L).getExchangeURL()), HttpStatus.OK);
    }
    @GetMapping("/fetch/exchange-two")
    public ResponseEntity<List<StockProduct>> fetchExchangeDataTwo() {
        return new ResponseEntity<>(marketDataService
                .fetchStockProduct(exchangeRepository.getById(2L).getExchangeURL()), HttpStatus.OK);
    }


    @PostMapping("subscribe/exchange-one")
    public ResponseEntity<?> subscribeFromExchangeOne() {
        return new ResponseEntity<>( marketDataService
                .subscribeToStockProducts(exchangeRepository.getById(1L).getExchangeURL() + "/subscription",
                        "https://localhost:8086/subscribe/market-data/exchange-one/callback"),HttpStatus.OK);
    }


    @PostMapping("subscribe/exchange-one/callback")
    public ResponseEntity<List<StockProduct>> callbackOne(@RequestBody List<StockProduct> stockProductList) {
       return new ResponseEntity<>(stockProductList, HttpStatus.OK);
    }

    @DeleteMapping("subscribe/exchange-one")
    public ResponseEntity<?> unsubscribeFromExchangeOne(@RequestBody String callbackUrl) {
        return new ResponseEntity<>( marketDataService
                .subscribeToStockProducts(exchangeRepository.getById(1L).getExchangeURL() + "/subscription",
                        callbackUrl),HttpStatus.OK);
    }
    @PostMapping("/subscribe/exchange-two")
    public ResponseEntity<?> subscribeFromExchangeTwo() {
        return new ResponseEntity<>( marketDataService
                .subscribeToStockProducts(exchangeRepository.getById(2L).getExchangeURL() + "/subscription",
                        "https://localhost:8086/subscribe/market-data/exchange-one/callback"), HttpStatus.OK);
    }


    @PostMapping("/subscribe/exchange-two/callback")
    public ResponseEntity<List<StockProduct>> callbackTwo(@RequestBody List<StockProduct> stockProductList) {
        return new ResponseEntity<>(stockProductList, HttpStatus.OK);
    }

    @DeleteMapping("/subscribe/exchange-two")
    public ResponseEntity<?> unsubscribeFromExchangeTwo(@RequestBody String callbackUrl) {
        return new ResponseEntity<>( marketDataService
                .subscribeToStockProducts(exchangeRepository.getById(2L)
                        .getExchangeURL() + "/subscription",
                        "https://localhost:8086/market-data/exchange-two/callback"), HttpStatus.OK);
    }

}
