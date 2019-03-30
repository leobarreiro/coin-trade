package org.javaleo.cointrade.server.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Symbol {

	REAL("BRL", "Real", "REAL"), DASH("DASH", "Dash", "DASH"), ETHEREUM("ETH", "Ethereum", "ETHEREUM"),
	BITCOIN("BTC", "Bitcoin", "BITCOIN"), BITCOIN_CASH("BTH", "Bitcoin Cash", "BITCOIN_CASH"),
	BITCOIN_GOLD("BTG", "Bitcoin Gold", "BITCOIN_GOLD"), LITECOIN("LTC", "Litecoin", "LITECOIN"),
	MONERO("XMR", "Monero", "MONERO"), ZCASH("ZEC", "ZCash", "ZCASH"), UNKNOWN("UNK", "Unknown", "");

	private String symbol;
	private String name;
	@JsonIgnore
	private String alternativeName;

	public static Symbol getFromName(String name) {
		for (Symbol smb : values()) {
			if (StringUtils.equalsIgnoreCase(smb.name, name)
					|| StringUtils.equalsIgnoreCase(smb.alternativeName, name)) {
				return smb;
			}
		}
		return Symbol.UNKNOWN;
	}

	public static Symbol getFromSymbol(String symbol) {
		for (Symbol smb : values()) {
			if (StringUtils.equalsIgnoreCase(smb.symbol, symbol)
					|| StringUtils.equalsIgnoreCase(smb.alternativeName, symbol)) {
				return smb;
			}
		}
		return Symbol.UNKNOWN;
	}

}
