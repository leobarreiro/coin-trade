package org.javaleo.cointrade.server.endpoints;

import java.util.List;

import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.repositories.ExchangeRepository;
import org.javaleo.cointrade.server.responses.ExchangeListResponse;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/exchange")
public class ExchangeEndpoint {

	@Autowired
	private ExchangeRepository excRepo;

	@GetMapping("/list")
	public ExchangeListResponse getExchangeList() {
		ExchangeListResponse rsp = new ExchangeListResponse();
		rsp.setSuccess(true);
		rsp.setTimeStamp(CoinTradeUtils.now());
		List<Exchange> exchanges = excRepo.findAll();
		rsp.setExchanges(exchanges);
		return rsp;
	}

}
