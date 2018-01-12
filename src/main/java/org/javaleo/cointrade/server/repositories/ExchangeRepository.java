package org.javaleo.cointrade.server.repositories;

import org.javaleo.cointrade.server.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

	@Query("select e from Exchange e where upper(e.name) = upper(?1) ")
	Exchange findOneByName(String name);

}
