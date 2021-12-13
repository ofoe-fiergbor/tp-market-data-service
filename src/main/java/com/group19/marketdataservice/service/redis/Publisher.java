package com.group19.marketdataservice.service.redis;

import com.group19.marketdataservice.domain.dto.StockProduct;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class Publisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;

    public Publisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    public void saveMessage(StockProduct message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }

}
