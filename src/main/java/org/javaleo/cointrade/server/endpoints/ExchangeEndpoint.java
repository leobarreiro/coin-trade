package org.javaleo.cointrade.server.endpoints;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.repositories.ExchangeRepository;
import org.javaleo.cointrade.server.responses.ExchangeListResponse;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/exchange")
public class ExchangeEndpoint {

	@Autowired
	private ExchangeRepository excRepo;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public ExchangeListResponse getExchangeList() {
		ExchangeListResponse rsp = new ExchangeListResponse();
		rsp.setTimeStamp(CoinTradeUtils.now());
		List<Exchange> exchanges = excRepo.findAll();
		rsp.setExchanges(exchanges);
		return rsp;
	}

}
