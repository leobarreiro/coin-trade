package org.javaleo.cointrade.server.repositories;

import java.util.List;

import org.javaleo.cointrade.server.entities.Currency;
import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.enums.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	Currency findOneByName(Symbol symbol);

	@Query("select distinct m.referenceCoin from Market m where m.exchange = ?1 order by m.referenceCoin.name")
	List<Currency> findReferenceCoinByExchange(Exchange exchange);

}
