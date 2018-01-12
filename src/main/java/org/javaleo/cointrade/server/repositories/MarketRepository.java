package org.javaleo.cointrade.server.repositories;

import java.util.List;

import org.javaleo.cointrade.server.entities.Currency;
import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarketRepository extends JpaRepository<Market, Long> {

	@Query("select m from Market m where m.exchange = ?1")
	List<Market> findByExchange(Exchange exchange);

	List<Market> findByExchangeAndReferenceCoin(Exchange exchange, Currency currency);

	@Query("select m from Market m where m.exchange = ?1 and lower(m.name) = lower(?2)")
	Market findOneByExchangeAndName(Exchange exc, String marketName);

	Market findOneByExchangeAndReferenceCoinAndChangeCoin(Exchange exchange, Currency referenceCoin, Currency changeCoin);

	List<Market> findByActiveTrue();

	List<Market> findByTraceTrue();

}
