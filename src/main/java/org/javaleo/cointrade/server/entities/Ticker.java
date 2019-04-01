package org.javaleo.cointrade.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ticker")
@SequenceGenerator(name = "seqTicker", sequenceName = "ticker_seq", allocationSize = 1, initialValue = 1)
public class Ticker implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ticker_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTicker")
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

	@Column(name = "time_ref")
	private Long timeReference;

	@Column(name = "tkr_year", scale = 4)
	@JsonIgnore
	private Integer year;

	@Column(name = "tkr_month", scale = 2)
	@JsonIgnore
	private Integer month;

	@Column(name = "tkr_day", scale = 2)
	@JsonIgnore
	private Integer day;

	@Column(name = "tkr_hour", scale = 2)
	@JsonIgnore
	private Integer hour;

	@Column(name = "tkr_minute", scale = 2)
	@JsonIgnore
	private Integer minute;

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

}
