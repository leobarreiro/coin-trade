package org.javaleo.cointrade.server.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Symbol {

		REAL("BRL", "Real", ""), DASH("DASH", "Dash", ""), ETHEREUM("ETH", "Ethereum", ""),
		BITCOIN("BTC", "Bitcoin", ""), BITCOIN_CASH("BTH", "Bitcoin Cash", "Bitcoin-Cash"),
		BITCOIN_GOLD("BTG", "Bitcoin Gold", "Bitcoin-Gold"), LITECOIN("LTC", "Litecoin", ""),
		SINGULARDTV("SINGLES", "SingularDTV", ""), MONERO("XMR", "Monero", ""),
		CROWN("CRW", "Crown", ""), ZCASH("ZEC", "ZCash", ""), MARTEX_COIN("MTX", "MartexCoin", ""),
		UNKNOWN("UNK", "Unknown", "");

	private String symbol;
	private String name;

	@JsonIgnore
	private String alternativeName;

	private Symbol(String symbol, String name, String alternativeName) {
		this.name = name;
		this.symbol = symbol;
		this.alternativeName = alternativeName;
	}

	public static Symbol getFromName(String name) {
		for (Symbol smb : values()) {
			if (StringUtils.equalsIgnoreCase(smb.name, name) || StringUtils.equalsIgnoreCase(smb.alternativeName, name)) {
				return smb;
			}
		}
		return Symbol.UNKNOWN;
	}

	public static Symbol getFromSymbol(String symbol) {
		for (Symbol smb : values()) {
			if (StringUtils.equalsIgnoreCase(smb.symbol, symbol)) {
				return smb;
			}
		}
		return Symbol.UNKNOWN;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlternativeName() {
		return alternativeName;
	}

	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}

}
