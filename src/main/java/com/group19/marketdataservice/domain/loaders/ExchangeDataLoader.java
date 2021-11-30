package com.group19.marketdataservice.domain.loaders;

import com.group19.marketdataservice.domain.model.Exchange;
import com.group19.marketdataservice.enums.ExchangeStatus;
import com.group19.marketdataservice.service.ExchangeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class ExchangeDataLoader {

    @Autowired
    private ExchangeService exchangeService;

    public void createExchangeData() {
        exchangeService.write("exchange_one", "https://exchange.matraining.com/md", ExchangeStatus.ACTIVE);
        exchangeService.write("exchange_two", "https://exchange2.matraining.com/md", ExchangeStatus.ACTIVE);

        List<Exchange> allExchanges = exchangeService.getAll();
        for (Exchange exchange: allExchanges) {
            log.info("{} loaded into the database.", exchange.getName());
        }
    }


}
