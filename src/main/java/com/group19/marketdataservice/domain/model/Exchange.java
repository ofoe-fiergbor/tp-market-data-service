package com.group19.marketdataservice.domain.model;

import com.group19.marketdataservice.enums.ExchangeStatus;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "exchanges")
@NoArgsConstructor
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "exchange_url")
    private String exchangeURL;

    @NotNull
    @Column(name = "status")
    private ExchangeStatus exchangeStatus;

    public Exchange(String name, String exchangeURL, ExchangeStatus exchangeStatus) {
        this.name = name;
        this.exchangeURL = exchangeURL;
        this.exchangeStatus = exchangeStatus;
    }
}
