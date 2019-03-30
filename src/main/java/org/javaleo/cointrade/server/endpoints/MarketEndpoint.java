package org.javaleo.cointrade.server.endpoints;

import java.text.MessageFormat;
import java.util.List;

import javax.websocket.server.PathParam;

import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.repositories.ExchangeRepository;
import org.javaleo.cointrade.server.repositories.MarketRepository;
import org.javaleo.cointrade.server.responses.MarketListResponse;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/market")
public class MarketEndpoint {

	@Autowired
	private ExchangeRepository excRepo;

	@Autowired
	private MarketRepository mktRepo;

	@GetMapping("/list/{excname}")
	public MarketListResponse getMarketsFromExchange(@PathParam("excname") String excName) {
		MarketListResponse mktRsp = new MarketListResponse();
		mktRsp.setTimeStamp(CoinTradeUtils.now());
		Exchange exc = excRepo.findOneByName(excName);
		if (exc == null) {
			mktRsp.setMessage(MessageFormat.format("Exchange called [{0}] not found.", excName));
			mktRsp.setSuccess(false);
			return mktRsp;
		}
		mktRsp.setExchange(exc);
		List<Market> markets = mktRepo.findByExchange(exc);
		mktRsp.setMarkets(markets);
		mktRsp.setSuccess(true);
		return mktRsp;
	}

}
