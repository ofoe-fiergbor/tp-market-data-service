package com.group19.marketdataservice.enums;

import lombok.Getter;

public enum ExchangeName {
    EXCHANGE_ONE("exchange-one"), EXCHANGE_TWO("exchange-two");

    @Getter
    private final String exchangeName;

    ExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }






}
