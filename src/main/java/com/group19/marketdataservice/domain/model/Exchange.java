package com.group19.marketdataservice.domain.model;

import com.group19.marketdataservice.enums.ExchangeStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "EXCHANGE")
    private String name;

    @NotNull
    @Column(name = "EXCHANGE_URL")
    private String exchangeURL;

    @NotNull
    @Column(name = "STATUS")
    private ExchangeStatus exchangeStatus;

    public Exchange(String name, String exchangeURL, ExchangeStatus exchangeStatus) {
        this.name = name;
        this.exchangeURL = exchangeURL;
        this.exchangeStatus = exchangeStatus;
    }
}
