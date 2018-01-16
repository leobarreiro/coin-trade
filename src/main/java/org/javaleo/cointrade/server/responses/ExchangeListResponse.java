package org.javaleo.cointrade.server.responses;

import java.util.List;

import org.javaleo.cointrade.server.entities.Exchange;

public class ExchangeListResponse extends CoinTradeBasicResponse {

	private static final long serialVersionUID = 1L;

	private long timeStamp;
	private String message;
	private Boolean success;
	private List<Exchange> exchanges;

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
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

}
