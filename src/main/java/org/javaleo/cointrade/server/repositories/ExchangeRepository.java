package org.javaleo.cointrade.server.repositories;

import org.javaleo.cointrade.server.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

	Exchange findOneByName(String name);

}
