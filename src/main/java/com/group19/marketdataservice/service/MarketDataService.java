package com.group19.marketdataservice.service;

import com.group19.marketdataservice.domain.model.StockProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MarketDataService {

    @Autowired
    private RestTemplate restTemplate;

    public String subscribeToStockProducts(String uri, String callback){
        HttpEntity<String> callbackUrl = new HttpEntity<>(callback);
        ResponseEntity<?> response = restTemplate.exchange(uri, HttpMethod.POST, callbackUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return (String) response.getBody();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
    }

    public List<StockProduct> fetchStockProduct(String uri) {
        return restTemplate.getForObject(uri, List.class );
    }

    public StockProduct fetchSingleStockProduct(String uri) {
        return restTemplate.getForObject(uri, StockProduct.class );
    }

}
