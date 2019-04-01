package org.javaleo.cointrade.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.javaleo.cointrade.server.enums.CandleInterval;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "candle")
@SequenceGenerator(name = "seqCandle", sequenceName = "candle_seq", allocationSize = 1, initialValue = 1)
public class Candle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCandle")
	@Column(name = "candle_id")
	@JsonIgnore
	private Long id;

	@ManyToOne
	@JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
	@JsonIgnore
	private Exchange exchange;

	@ManyToOne
	@JoinColumn(name = "market_id", referencedColumnName = "market_id")
	@JsonIgnore
	private Market market;

	@Column(name = "opening")
	private Double opening;

	@Column(name = "closure")
	private Double closure;

	@Column(name = "lowest")
	private Double lowest;

	@Column(name = "highest")
	private Double highest;

	@Column(name = "currency_amount")
	private Double currencyAmount;

	@Column(name = "mkcap_amount")
	private Double marketCapAmount;

	@Column(name = "dt_collected")
	private Long collectedTime;

	@Column(name = "cd_year", length = 4)
	private Integer year;

	@Column(name = "cd_month", length = 2)
	private Integer month;

	@Column(name = "cd_day", length = 2)
	private Integer day;

	@Column(name = "cd_hour", length = 2)
	private Integer hour;

	@Column(name = "cd_minute", length = 2)
	private Integer minute;

	@Enumerated(EnumType.STRING)
	@Column(name = "cd_interval", length = 20)
	@JsonIgnore
	private CandleInterval interval;

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

	public Double getOpening() {
		return opening;
	}

	public void setOpening(Double opening) {
		this.opening = opening;
	}

	public Double getClosure() {
		return closure;
	}

	public void setClosure(Double closure) {
		this.closure = closure;
	}

	public Double getLowest() {
		return lowest;
	}

	public void setLowest(Double lowest) {
		this.lowest = lowest;
	}

	public Double getHighest() {
		return highest;
	}

	public void setHighest(Double highest) {
		this.highest = highest;
	}

	public Double getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(Double currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public Double getMarketCapAmount() {
		return marketCapAmount;
	}

	public void setMarketCapAmount(Double marketCapAmount) {
		this.marketCapAmount = marketCapAmount;
	}

	public Long getCollectedTime() {
		return collectedTime;
	}

	public void setCollectedTime(Long collected) {
		this.collectedTime = collected;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public CandleInterval getInterval() {
		return interval;
	}

	public void setInterval(CandleInterval interval) {
		this.interval = interval;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closure == null) ? 0 : closure.hashCode());
		result = prime * result + ((marketCapAmount == null) ? 0 : marketCapAmount.hashCode());
		result = prime * result + ((collectedTime == null) ? 0 : collectedTime.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((highest == null) ? 0 : highest.hashCode());
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interval == null) ? 0 : interval.hashCode());
		result = prime * result + ((lowest == null) ? 0 : lowest.hashCode());
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime * result + ((minute == null) ? 0 : minute.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((opening == null) ? 0 : opening.hashCode());
		result = prime * result + ((currencyAmount == null) ? 0 : currencyAmount.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candle other = (Candle) obj;
		if (closure == null) {
			if (other.closure != null)
				return false;
		} else if (!closure.equals(other.closure))
			return false;
		if (marketCapAmount == null) {
			if (other.marketCapAmount != null)
				return false;
		} else if (!marketCapAmount.equals(other.marketCapAmount))
			return false;
		if (collectedTime == null) {
			if (other.collectedTime != null)
				return false;
		} else if (!collectedTime.equals(other.collectedTime))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (exchange == null) {
			if (other.exchange != null)
				return false;
		} else if (!exchange.equals(other.exchange))
			return false;
		if (highest == null) {
			if (other.highest != null)
				return false;
		} else if (!highest.equals(other.highest))
			return false;
		if (hour == null) {
			if (other.hour != null)
				return false;
		} else if (!hour.equals(other.hour))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interval != other.interval)
			return false;
		if (lowest == null) {
			if (other.lowest != null)
				return false;
		} else if (!lowest.equals(other.lowest))
			return false;
		if (market == null) {
			if (other.market != null)
				return false;
		} else if (!market.equals(other.market))
			return false;
		if (minute == null) {
			if (other.minute != null)
				return false;
		} else if (!minute.equals(other.minute))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (opening == null) {
			if (other.opening != null)
				return false;
		} else if (!opening.equals(other.opening))
			return false;
		if (currencyAmount == null) {
			if (other.currencyAmount != null)
				return false;
		} else if (!currencyAmount.equals(other.currencyAmount))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

}
