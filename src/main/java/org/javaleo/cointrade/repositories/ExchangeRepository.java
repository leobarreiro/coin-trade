package org.javaleo.cointrade.repositories;

import org.javaleo.cointrade.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

	Exchange findOneByName(String name);

}
