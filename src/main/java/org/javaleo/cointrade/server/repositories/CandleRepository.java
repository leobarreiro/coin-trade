package org.javaleo.cointrade.server.repositories;

import java.util.List;

import org.javaleo.cointrade.server.entities.Candle;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.enums.CandleInterval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandleRepository extends JpaRepository<Candle, Long> {

	List<Candle> findByMarketAndIntervalAndCollectedTimeGreaterThanEqual(Market mkt, CandleInterval itr, Long col);

}
