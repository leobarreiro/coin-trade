package org.javaleo.cointrade.server.endpoints;

import java.text.MessageFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javaleo.cointrade.server.entities.Candle;
import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.enums.CandleInterval;
import org.javaleo.cointrade.server.repositories.CandleRepository;
import org.javaleo.cointrade.server.repositories.ExchangeRepository;
import org.javaleo.cointrade.server.repositories.MarketRepository;
import org.javaleo.cointrade.server.responses.CandleListResponse;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/candles")
public class CandleEndpoint {

	@Autowired
	private ExchangeRepository excRepo;

	@Autowired
	private MarketRepository mktRepo;

	@Autowired
	private CandleRepository candleRepo;

	@GET
	@Path("/{excname}/{mktname}/{itn}")
	@Produces(MediaType.APPLICATION_JSON)
	public CandleListResponse getCandlesByExchangeMarketAndInterval(@PathParam("excname") String exchangeName, @PathParam("mktname") String marketName, @PathParam("itn") String candleInterval) {
		CandleListResponse rsp = new CandleListResponse();
		rsp.setTimeStamp(CoinTradeUtils.now());

		Exchange exc = excRepo.findOneByName(exchangeName);
		if (exc == null) {
			rsp.setMessage(MessageFormat.format("Exchange called [{0}] not found.", exchangeName));
			return rsp;
		}

		Market mkt = mktRepo.findOneByExchangeAndName(exc, marketName);
		if (mkt == null) {
			rsp.setMessage(MessageFormat.format("Market called [{0}] not found.", marketName));
		}

		CandleInterval interval = CandleInterval.getByName(candleInterval);
		if (interval == null) {
			rsp.setMessage(MessageFormat.format("Interval called [{0}] not found.", interval));
		}

		List<Candle> candles = candleRepo.findByMarketAndIntervalAndCollectedTimeGreaterThanEqual(mkt, interval, CoinTradeUtils.aDayAgo());

		rsp.setSince(CoinTradeUtils.aDayAgo());
		rsp.setUntil(CoinTradeUtils.now());
		rsp.setExchange(exc);
		rsp.setMarket(mkt);
		rsp.setCandleInterval(interval);
		rsp.setCandles(candles);

		return rsp;
	}

}
