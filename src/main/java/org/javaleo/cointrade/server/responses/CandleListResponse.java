package org.javaleo.cointrade.server.responses;

import java.util.List;

import org.javaleo.cointrade.server.entities.Candle;
import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.entities.Ticker;
import org.javaleo.cointrade.server.enums.CandleInterval;

public class CandleListResponse extends CoinTradeBasicResponse {

	private static final long serialVersionUID = 1L;

	private long timeStamp;

	private Boolean success;

	private String message;

	private long since;

	private long until;

	private Exchange exchange;

	private Market market;

	private Ticker lastTicker;

	private CandleInterval candleInterval;

	private List<Candle> candles;

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

	public long getSince() {
		return since;
	}

	public void setSince(long since) {
		this.since = since;
	}

	public long getUntil() {
		return until;
	}

	public void setUntil(long until) {
		this.until = until;
	}

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public Ticker getLastTicker() {
		return lastTicker;
	}

	public void setLastTicker(Ticker lastTicker) {
		this.lastTicker = lastTicker;
	}

	public CandleInterval getCandleInterval() {
		return candleInterval;
	}

	public void setCandleInterval(CandleInterval candleInterval) {
		this.candleInterval = candleInterval;
	}

	public List<Candle> getCandles() {
		return candles;
	}

	public void setCandles(List<Candle> candles) {
		this.candles = candles;
	}

}
