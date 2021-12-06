package com.group19.marketdataservice.configuration;

import com.group19.marketdataservice.domain.loaders.ExchangeDataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineConfig {

    @Bean
    public CommandLineRunner createExchangeData(ExchangeDataLoader loader) {
        return args -> {
            loader.createExchangeData();
        };
    }
}
