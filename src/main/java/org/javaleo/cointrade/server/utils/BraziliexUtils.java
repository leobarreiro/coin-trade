package org.javaleo.cointrade.server.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.javaleo.cointrade.server.entities.Currency;
import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.entities.Ticker;
import org.javaleo.cointrade.server.enums.Symbol;
import org.javaleo.cointrade.server.stubs.BraziliexCurrencyStub;
import org.javaleo.cointrade.server.stubs.BraziliexListCurrencyStub;
import org.javaleo.cointrade.server.stubs.BraziliexListTickerStub;
import org.javaleo.cointrade.server.stubs.BraziliexTickerStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BraziliexUtils {

	private static final Logger LOG = LoggerFactory.getLogger(BraziliexUtils.class);

	public static Currency getCurrencyFromStub(BraziliexCurrencyStub stub) {
		Currency cry = new Currency();
		cry.setName(Symbol.getFromName(stub.getName()));
		cry.setDescription("Fonte: API Braziliex");
		return cry;
	}

	public static List<BraziliexCurrencyStub> listCurrencyStubFromResponse(BraziliexListCurrencyStub response) {
		List<BraziliexCurrencyStub> stubs = new ArrayList<>();
		Field[] fields = response.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.getType().equals(BraziliexCurrencyStub.class)) {
				try {
					BraziliexCurrencyStub stub = (BraziliexCurrencyStub) f.get(response);
					stubs.add(stub);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					LOG.error(e.getMessage());
				}
			}
		}
		return stubs;
	}

	public static boolean currencyExists(BraziliexCurrencyStub stub, List<Currency> currencies) {
		Symbol symbol = Symbol.getFromName(stub.getName());
		if (symbol != null) {
			for (Currency c : currencies) {
				if (symbol.equals(c.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<BraziliexTickerStub> listTickerStubFromResponse(BraziliexListTickerStub response) {
		List<BraziliexTickerStub> stubs = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		Field[] fields = response.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.getType().equals(BraziliexTickerStub.class)) {
				try {
					BraziliexTickerStub stub = (BraziliexTickerStub) f.get(response);
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

	public static boolean marketExists(BraziliexTickerStub stub, List<Market> markets) {
		Market market = getMarketBySymbols(stub.getMarket(), markets);
		return (market != null);
	}

	public static boolean tickerExists(BraziliexTickerStub stub, Market market, List<Ticker> tickers) {
		for (Ticker tic : tickers) {
			if (tic.getMarket().equals(market) && tic.getTimeReference().equals(stub.getTimeReference())) {
				return true;
			}
		}
		return false;
	}

	public static Market getMarketBySymbols(String brzMkt, List<Market> markets) {
		if (StringUtils.isBlank(brzMkt)) {
			return null;
		}
		String[] crs = StringUtils.split(brzMkt, "_");
		Symbol refSymbol = Symbol.getFromSymbol(crs[1]);
		Symbol changeSymbol = Symbol.getFromSymbol(crs[0]);
		if (refSymbol.equals(Symbol.UNKNOWN) || changeSymbol.equals(Symbol.UNKNOWN)) {
			return null;
		}

		for (Market m : markets) {
			if (m.getReferenceCoin().getName().equals(refSymbol) && m.getChangeCoin().getName().equals(changeSymbol)) {
				return m;
			}
		}

		return null;
	}

	public static Ticker getTickerFromStub(Exchange exc, Market mkt, BraziliexTickerStub st)
			throws NumberFormatException {
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

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.setTimeInMillis(st.getTimeReference());
		tk.setTimeReference(st.getTimeReference());
		tk.setYear(c.get(Calendar.YEAR));
		tk.setMonth(c.get(Calendar.MONTH) + 1);
		tk.setDay(c.get(Calendar.DAY_OF_MONTH));
		tk.setHour(c.get(Calendar.HOUR_OF_DAY));
		tk.setMinute(c.get(Calendar.MINUTE));

		return tk;
	}

}
