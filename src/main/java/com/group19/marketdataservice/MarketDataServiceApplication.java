package com.group19.marketdataservice;

import com.group19.marketdataservice.domain.loaders.ExchangeDataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class MarketDataServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(MarketDataServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner createExchangeData(ExchangeDataLoader loader) {
		return args -> {
			loader.createExchangeData();
		};
	}

}
