package org.javaleo.cointrade.repositories;

import java.util.List;

import org.javaleo.cointrade.entities.Currency;
import org.javaleo.cointrade.entities.Exchange;
import org.javaleo.cointrade.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarketRepository extends JpaRepository<Market, Long> {

	@Query("select m from Market m where m.exchange = ?1")
	List<Market> findByExchange(Exchange exchange);

	Market findOneByExchangeAndReferenceCoinAndChangeCoin(Exchange exchange, Currency referenceCoin, Currency changeCoin);

}
