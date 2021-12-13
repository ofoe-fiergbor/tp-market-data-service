package com.group19.marketdataservice.controller;

import com.group19.marketdataservice.service.MarketDataService;
import com.group19.marketdataservice.service.redis.Subscriber;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market-data")
public class MarketDataController {

    private final
    MarketDataService marketDataService;

    private final
    RedisTemplate<String, Object> template;

    private final
    ChannelTopic channelTopic;

    private final
    Subscriber receiver;

    @Value("${system.exchange_one}")
    private String EXCHANGE_ONE_URL;

    @Value("${system.exchange_two}")
    private String EXCHANGE_TWO_URL;

    @Autowired
    public MarketDataController(RedisTemplate<String, Object> template, ChannelTopic channelTopic, Subscriber receiver, MarketDataService marketDataService) {
        this.template = template;
        this.channelTopic = channelTopic;
        this.receiver = receiver;
        this.marketDataService = marketDataService;
    }


    @GetMapping("/fetch/exchange-one/{product}")
    @Operation(summary = "Fetch market data for a single product in exchange one")
    public ResponseEntity<?> fetchSingleProductOne(@PathVariable("product") String product) {
        return new ResponseEntity<>(marketDataService.fetchSingleStockProduct(EXCHANGE_ONE_URL+"/"+product), HttpStatus.OK);
    }

    @GetMapping("/fetch/exchange-two/{product}")
    @Operation(summary = "Fetch market data for a single product in exchange two")
    public ResponseEntity<?> fetchSingleProductTwo(@PathVariable String product) {
        return new ResponseEntity<>(marketDataService.fetchSingleStockProduct(EXCHANGE_TWO_URL+"/"+product), HttpStatus.OK);
    }

    @GetMapping("/fetch/exchange-one")
    @Operation(summary = "Rest api for exchange one")
    public ResponseEntity<?> fetchExchangeDataOne() {
         return new ResponseEntity<>(marketDataService.fetchStockProduct(EXCHANGE_ONE_URL), HttpStatus.OK);
    }


    @GetMapping("/fetch/exchange-two")
    @Operation(summary = "Rest api for exchange two")
    public ResponseEntity<?> fetchExchangeDataTwo() {
        return new ResponseEntity<>(marketDataService.fetchStockProduct(EXCHANGE_TWO_URL), HttpStatus.OK);
    }


    @PostMapping("/subscribe/exchange-one")
    @Operation(summary = "Subscribe to exchange one")
    public ResponseEntity<?> subscribeFromExchangeOne() {
        return new ResponseEntity<>(marketDataService.subscribeToStockProducts(EXCHANGE_ONE_URL + "/subscription",
                "https://localhost:8086/subscribe/market-data/exchange-one/callback"),HttpStatus.OK);

    }


    @PostMapping("/subscribe/exchange-one/callback")
    @Operation(summary = "Callback url for exchange one")
    public void callbackOne(@RequestBody Object marketData) {
        //redis implementation
        template.convertAndSend(channelTopic.getTopic(), marketData.toString());
    }

    @DeleteMapping("/subscribe/exchange-one")
    @Operation(summary = "Unsubscribe to exchange one")
    public ResponseEntity<?> unsubscribeFromExchangeOne(@RequestBody String callbackUrl) {
        return new ResponseEntity<>( marketDataService
                .subscribeToStockProducts(EXCHANGE_ONE_URL + "/subscription", callbackUrl),HttpStatus.OK);

    }

    @GetMapping("/subscribe/exchange-one/redis-md")
    @Operation(summary = "Fetch market data from redis server --> exchange one")
    public ResponseEntity<?> fetchRedisMDExchangeOne(){
        return new ResponseEntity<>(receiver.getMessage(), HttpStatus.OK);
    }

    @PostMapping("/subscribe/exchange-two")
    @Operation(summary = "Subscribe to exchange two")
    public ResponseEntity<?> subscribeFromExchangeTwo() {
        return new ResponseEntity<>( marketDataService
                   .subscribeToStockProducts(EXCHANGE_TWO_URL+ "/subscription",
                           "https://localhost:8086/subscribe/market-data/exchange-one/callback"), HttpStatus.OK);
    }

    @PostMapping("/subscribe/exchange-two/callback")
    @Operation(summary = "Callback url for exchange two")
    public void callbackTwo(@RequestBody Object marketData) {
        //redis implementation
        template.convertAndSend(channelTopic.getTopic(), marketData.toString());
    }

    @DeleteMapping("/subscribe/exchange-two")
    @Operation(summary = "Unsubscribe to exchange two")
    public ResponseEntity<?> unsubscribeFromExchangeTwo(@RequestBody String callbackUrl) {
        return new ResponseEntity<>( marketDataService
                   .subscribeToStockProducts(EXCHANGE_TWO_URL + "/subscription",
                           "https://localhost:8086/market-data/exchange-two/callback"), HttpStatus.OK);

    }

    @GetMapping("/subscribe/exchange-two/redis-md")
    @Operation(summary = "Fetch market data from redis server --> exchange two")
    public ResponseEntity<?> fetchRedisMDExchangeTwo(){
        return new ResponseEntity<>(receiver.getMessage(), HttpStatus.OK);
    }

}
