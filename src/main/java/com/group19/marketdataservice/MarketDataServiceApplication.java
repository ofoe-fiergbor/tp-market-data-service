package com.group19.marketdataservice;

import com.group19.marketdataservice.model.MarketData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(MarketDataServiceConfigProperties.class)
public class MarketDataServiceApplication {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

//	I will come and remove you
	public static void main(String[] args) {
		SpringApplication.run(MarketDataServiceApplication.class, args);
	}

	@Autowired
	private MarketDataServiceConfigProperties marketDataServiceConfigProperties;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		String host = marketDataServiceConfigProperties.getServer();
		int port = marketDataServiceConfigProperties.getPort();
		return new JedisConnectionFactory(new RedisStandaloneConfiguration("hello",8080));
	}

	@Bean
	public RedisTemplate<String, MarketData> redisTemplate(){
		RedisTemplate<String, MarketData> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}




}
