package com.group19.marketdataservice.service.redis;

import lombok.Getter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class Subscriber implements MessageListener {

    @Getter
    private Message message;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        this.message = message;
    }
}
