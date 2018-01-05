package org.javaleo.cointrade.braziliex.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.javaleo.cointrade.braziliex.stubs.BrzCurrencyStub;
import org.javaleo.cointrade.braziliex.stubs.BrzListCurrencyStub;
import org.javaleo.cointrade.braziliex.stubs.BrzListTickerStub;
import org.javaleo.cointrade.braziliex.stubs.BrzTickerStub;
import org.javaleo.cointrade.entities.Currency;
import org.javaleo.cointrade.entities.Exchange;
import org.javaleo.cointrade.entities.Market;
import org.javaleo.cointrade.entities.Ticker;
import org.javaleo.cointrade.enums.Symbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BraziliexUtils {

	private static final Logger LOG = LoggerFactory.getLogger(BraziliexUtils.class);

	public static Currency getCurrencyFromStub(BrzCurrencyStub stub) {
		Currency cry = new Currency();
		cry.setSymbol(Symbol.getFromName(stub.getName()));
		cry.setDescription("Fonte: API Braziliex");
		return cry;
	}

	public static List<BrzCurrencyStub> listCurrencyStubFromResponse(BrzListCurrencyStub response) {
		List<BrzCurrencyStub> stubs = new ArrayList<>();
		Field[] fields = response.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.getType().equals(BrzCurrencyStub.class)) {
				try {
					BrzCurrencyStub stub = (BrzCurrencyStub) f.get(response);
					stubs.add(stub);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					LOG.error(e.getMessage());
				}
			}
		}
		return stubs;
	}

	public static boolean currencyExists(BrzCurrencyStub stub, List<Currency> currencies) {
		Symbol symbol = Symbol.getFromName(stub.getName());
		if (symbol != null) {
			for (Currency c : currencies) {
				if (symbol.equals(c.getSymbol())) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<BrzTickerStub> listTickerStubFromResponse(BrzListTickerStub response) {
		List<BrzTickerStub> stubs = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		Field[] fields = response.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.getType().equals(BrzTickerStub.class)) {
				try {
					BrzTickerStub stub = (BrzTickerStub) f.get(response);
					if (stub.getTimeReference() == null) {
						stub.setTimeReference(c.getTimeInMillis());
					}
					stubs.add(stub);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					LOG.error(e.getMessage());
				}
			}
		}
		return stubs;
	}

	public static boolean marketExists(BrzTickerStub stub, List<Market> markets) {
		Market market = getMarketBySymbols(stub.getMarket(), markets);
		return (market != null);
	}

	public static boolean tickerExists(BrzTickerStub stub, Market market, List<Ticker> tickers) {
		for (Ticker tic : tickers) {
			if (tic.getMarket().equals(market) && tic.getTimeReference().equals(stub.getTimeReference())) {
				return true;
			}
		}
		return false;
	}

	public static Market getMarketBySymbols(String brzMkt, List<Market> markets) {
		String[] crs = StringUtils.split(brzMkt, "_");
		Symbol refSymbol = Symbol.getFromSymbol(crs[1]);
		Symbol changeSymbol = Symbol.getFromSymbol(crs[0]);
		if (refSymbol.equals(Symbol.UNKNOWN) || changeSymbol.equals(Symbol.UNKNOWN)) {
			return null;
		}

		for (Market m : markets) {
			if (m.getReferenceCoin().getSymbol().equals(refSymbol) && m.getChangeCoin().getSymbol().equals(changeSymbol)) {
				return m;
			}
		}

		return null;
	}

	public static Ticker getTickerFromStub(Exchange exc, Market mkt, BrzTickerStub st) throws NumberFormatException {
		Ticker tk = new Ticker();
		tk.setExchange(exc);
		tk.setMarket(mkt);
		tk.setCurrencyVolume(Double.valueOf(st.getBaseVolume()));
		tk.setCurrencyVolumeDay(Double.valueOf(st.getBaseVolume24()));
		tk.setHighestBid(Double.valueOf(st.getHighestBid()));
		tk.setHighestBidDay(Double.valueOf(st.getHighestBid24()));
		tk.setLowestAsk(Double.valueOf(st.getLowestAsk()));
		tk.setLowestAskDay(Double.valueOf(st.getLowestAsk24()));
		tk.setMarketCap(Double.valueOf(st.getQuoteVolume()));
		tk.setMarketCapDay(Double.valueOf(st.getQuoteVolume24()));
		tk.setPercentChange(Double.valueOf(st.getPercentChange()));

		Double highestBid = tk.getHighestBid();
		Double lowestAsk = tk.getLowestAsk();
		Double spread = ((lowestAsk - highestBid) * 100) / lowestAsk;
		tk.setSpread(spread);

		tk.setTimeReference(st.getTimeReference());
		return tk;
	}

}
