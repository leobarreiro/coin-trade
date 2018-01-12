package org.javaleo.cointrade.server.responses;

import java.util.List;

import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;

public class MarketListResponse extends CoinTradeBasicResponse {

	private static final long serialVersionUID = 1L;

	private long timeStamp;
	private String message;
	private Boolean ok;

	private Exchange exchange;
	private List<Market> markets;

	@Override
	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public List<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
	}

}
