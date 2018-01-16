package org.javaleo.cointrade.server.endpoints;

import java.text.MessageFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.repositories.ExchangeRepository;
import org.javaleo.cointrade.server.repositories.MarketRepository;
import org.javaleo.cointrade.server.responses.MarketListResponse;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/market")
public class MarketEndpoint {

	@Autowired
	private ExchangeRepository excRepo;

	@Autowired
	private MarketRepository mktRepo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list/{excname}")
	public MarketListResponse getMarketsFromExchange(@PathParam("excname") String excName) {
		MarketListResponse mktRsp = new MarketListResponse();
		mktRsp.setTimeStamp(CoinTradeUtils.now());
		Exchange exc = excRepo.findOneByName(excName);
		if (exc == null) {
			mktRsp.setMessage(MessageFormat.format("Exchange called [{0}] not found.", excName));
			mktRsp.setOk(false);
			return mktRsp;
		}
		mktRsp.setExchange(exc);
		List<Market> markets = mktRepo.findByExchange(exc);
		mktRsp.setMarkets(markets);
		mktRsp.setOk(true);
		return mktRsp;
	}

}
