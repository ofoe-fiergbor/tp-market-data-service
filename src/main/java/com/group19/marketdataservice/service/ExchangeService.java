package com.group19.marketdataservice.service;

import com.group19.marketdataservice.domain.model.Exchange;
import com.group19.marketdataservice.domain.repository.ExchangeRepository;
import com.group19.marketdataservice.enums.ExchangeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeService {
    @Autowired
    private ExchangeRepository exchangeRepository;

    public void write(String exchange, String exchangeURL, ExchangeStatus exchangeStatus) {
        Exchange newExchange = new Exchange(exchange, exchangeURL, exchangeStatus);
        exchangeRepository.save(newExchange);
    }

    public List<Exchange> getAll() {
        return exchangeRepository.findAll();
    }

}
