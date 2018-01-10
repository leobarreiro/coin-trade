package org.javaleo.cointrade.server.responses;

import java.util.List;

import org.javaleo.cointrade.server.entities.Candle;
import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.enums.CandleInterval;

import com.google.gson.annotations.SerializedName;

public class CandleListResponse extends CoinTradeBasicResponse {

	private static final long serialVersionUID = 1L;

	@SerializedName("timestamp")
	private long timeStamp;

	@SerializedName("message")
	private String message;

	@SerializedName("help-message")
	private String helpMessage;

	@SerializedName("since")
	private long since;

	@SerializedName("until")
	private long until;

	@SerializedName("exchange")
	private Exchange exchange;

	@SerializedName("market")
	private Market market;

	@SerializedName("interval")
	private CandleInterval candleInterval;

	@SerializedName("candles")
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
	public String getHelpMessage() {
		return helpMessage;
	}

	public void setHelpMessage(String helpMessage) {
		this.helpMessage = helpMessage;
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
