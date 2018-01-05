package org.javaleo.cointrade.repositories;

import java.util.List;

import org.javaleo.cointrade.entities.Exchange;
import org.javaleo.cointrade.entities.Market;
import org.javaleo.cointrade.entities.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TickerRepository extends JpaRepository<Ticker, Long> {

	// ExchangeAndMarketAndTimeReferenceGreaterThanEqualAndTimeReferenceLessThanEqual
	@Query("select t from Ticker t where t.exchange = ?1 and t.market = ?2 and t.timeReference >= ?3 and t.timeReference <= ?4")
	List<Ticker> findTickerByInterval(Exchange exchange, Market market, Long dtIni, Long dtFim);

	List<Ticker> findTickerByExchangeAndTimeReferenceGreaterThanEqual(Exchange exchange, Long timeRef);

}
