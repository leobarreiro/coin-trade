package org.javaleo.cointrade.server.stubs;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BraziliexTickerStub implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("active")
	private Integer active;

	@SerializedName("market")
	private String market;

	@SerializedName("last")
	private String last;

	@SerializedName("percentChange")
	private String percentChange;

	@SerializedName("baseVolume")
	private String baseVolume;

	@SerializedName("quoteVolume")
	private String quoteVolume;

	@SerializedName("highestBid")
	private String highestBid;

	@SerializedName("lowestAsk")
	private String lowestAsk;

	@SerializedName("baseVolume24")
	private String baseVolume24;

	@SerializedName("quoteVolume24")
	private String quoteVolume24;

	@SerializedName("highestBid24")
	private String highestBid24;

	@SerializedName("lowestAsk24")
	private String lowestAsk24;

	private Long timeReference;

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	public String getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(String baseVolume) {
		this.baseVolume = baseVolume;
	}

	public String getQuoteVolume() {
		return quoteVolume;
	}

	public void setQuoteVolume(String quoteVolume) {
		this.quoteVolume = quoteVolume;
	}

	public String getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(String highestBid) {
		this.highestBid = highestBid;
	}

	public String getLowestAsk() {
		return lowestAsk;
	}

	public void setLowestAsk(String lowestAsk) {
		this.lowestAsk = lowestAsk;
	}

	public String getBaseVolume24() {
		return baseVolume24;
	}

	public void setBaseVolume24(String baseVolume24) {
		this.baseVolume24 = baseVolume24;
	}

	public String getQuoteVolume24() {
		return quoteVolume24;
	}

	public void setQuoteVolume24(String quoteVolume24) {
		this.quoteVolume24 = quoteVolume24;
	}

	public String getHighestBid24() {
		return highestBid24;
	}

	public void setHighestBid24(String highestBid24) {
		this.highestBid24 = highestBid24;
	}

	public String getLowestAsk24() {
		return lowestAsk24;
	}

	public void setLowestAsk24(String lowestAsk24) {
		this.lowestAsk24 = lowestAsk24;
	}

	public Long getTimeReference() {
		return timeReference;
	}

	public void setTimeReference(Long timeReference) {
		this.timeReference = timeReference;
	}

}
