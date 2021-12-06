package com.group19.marketdataservice.domain.repository;

import com.group19.marketdataservice.domain.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {}
