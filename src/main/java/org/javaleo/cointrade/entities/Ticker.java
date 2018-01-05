package org.javaleo.cointrade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ticker")
public class Ticker implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ticker_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
	private Exchange exchange;

	@ManyToOne
	@JoinColumn(name = "market_id", referencedColumnName = "market_id")
	private Market market;

	@Column(name = "time_ref")
	private Long timeReference;

	// highestBid - maior preço ofertado para compra
	@Column(name = "highest_bid")
	private Double highestBid;

	// lowestAsk menor preco pedido para venda
	@Column(name = "lowest_ask")
	private Double lowestAsk;

	@Column(name = "spread")
	private Double spread;

	@Column(name = "pct_change")
	private Double percentChange;

	// highestBid24 - maior preço ofertado para compra
	@Column(name = "highest_bid_day")
	private Double highestBidDay;

	// lowestAsk24 - menor preco pedido para venda
	@Column(name = "lowest_ask_day")
	private Double lowestAskDay;

	// baseVolume
	@Column(name = "currency_vol")
	private Double currencyVolume;

	// baseVolume24
	@Column(name = "currency_vol_day")
	private Double currencyVolumeDay;

	// quoteVolume
	@Column(name = "market_cap")
	private Double marketCap;

	// quoteVolume24
	@Column(name = "market_cap_day")
	private Double marketCapDay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getTimeReference() {
		return timeReference;
	}

	public void setTimeReference(Long timeReference) {
		this.timeReference = timeReference;
	}

	public Double getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(Double highestBid) {
		this.highestBid = highestBid;
	}

	public Double getLowestAsk() {
		return lowestAsk;
	}

	public void setLowestAsk(Double lowestAsk) {
		this.lowestAsk = lowestAsk;
	}

	public Double getSpread() {
		return spread;
	}

	public void setSpread(Double spread) {
		this.spread = spread;
	}

	public Double getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}

	public Double getHighestBidDay() {
		return highestBidDay;
	}

	public void setHighestBidDay(Double highestBidDay) {
		this.highestBidDay = highestBidDay;
	}

	public Double getLowestAskDay() {
		return lowestAskDay;
	}

	public void setLowestAskDay(Double lowestAskDay) {
		this.lowestAskDay = lowestAskDay;
	}

	public Double getCurrencyVolume() {
		return currencyVolume;
	}

	public void setCurrencyVolume(Double currencyVolume) {
		this.currencyVolume = currencyVolume;
	}

	public Double getCurrencyVolumeDay() {
		return currencyVolumeDay;
	}

	public void setCurrencyVolumeDay(Double currencyVolumeDay) {
		this.currencyVolumeDay = currencyVolumeDay;
	}

	public Double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	public Double getMarketCapDay() {
		return marketCapDay;
	}

	public void setMarketCapDay(Double marketCapDay) {
		this.marketCapDay = marketCapDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime * result + ((timeReference == null) ? 0 : timeReference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Ticker other = (Ticker) obj;
		if (exchange == null) {
			if (other.exchange != null) return false;
		} else if (!exchange.equals(other.exchange)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (market == null) {
			if (other.market != null) return false;
		} else if (!market.equals(other.market)) return false;
		if (timeReference == null) {
			if (other.timeReference != null) return false;
		} else if (!timeReference.equals(other.timeReference)) return false;
		return true;
	}

}
