package org.javaleo.cointrade.server.schedules;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.javaleo.cointrade.server.entities.Currency;
import org.javaleo.cointrade.server.entities.Exchange;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.entities.Ticker;
import org.javaleo.cointrade.server.enums.Symbol;
import org.javaleo.cointrade.server.repositories.CurrencyRepository;
import org.javaleo.cointrade.server.repositories.ExchangeRepository;
import org.javaleo.cointrade.server.repositories.MarketRepository;
import org.javaleo.cointrade.server.repositories.TickerRepository;
import org.javaleo.cointrade.server.requests.CoinTradeBasicRequest;
import org.javaleo.cointrade.server.stubs.BraziliexCurrencyStub;
import org.javaleo.cointrade.server.stubs.BraziliexListCurrencyStub;
import org.javaleo.cointrade.server.stubs.BraziliexListTickerStub;
import org.javaleo.cointrade.server.stubs.BraziliexTickerStub;
import org.javaleo.cointrade.server.utils.BraziliexUtils;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BraziliexScheduled {

	private static final String BRAZILIEX_URL = "https://braziliex.com";
	private static final String BRAZILIEX = "Braziliex";
	private static final String URL_CURRENCIES = "https://braziliex.com/api/v1/public/currencies";
	private static final String URL_TICKERS = "https://braziliex.com/api/v1/public/ticker";

	@Autowired
	private CurrencyRepository currencyRepo;

	@Autowired
	private ExchangeRepository exchangeRepo;

	@Autowired
	private MarketRepository marketRepo;

	@Autowired
	private TickerRepository tickerRepo;

	@PostConstruct
	public void verifyBraziliexExchange() {
		log.info("Starting verification of Braziliex Exchange.");
		Exchange exch = exchangeRepo.findOneByName(BRAZILIEX);
		if (exch == null) {
			exch = new Exchange();
			exch.setName(BRAZILIEX);
			exch.setUrl(BRAZILIEX_URL);
			exchangeRepo.save(exch);
		}
		setupCurrencies();
		setupTickers();
	}

	public void setupCurrencies() {
		log.info("Starting set up of Braziliex currencies");
		List<Currency> currencies = currencyRepo.findAll();
		try {
			CoinTradeBasicRequest rsp = CoinTradeUtils.executeGetRequest(URL_CURRENCIES);
			if (rsp.getHttpResponseCode() == HttpStatus.SC_OK) {
				Gson gson = CoinTradeUtils.createGson();
				BraziliexListCurrencyStub listCurrency = gson.fromJson(rsp.getResponseContent(),
						BraziliexListCurrencyStub.class);
				List<BraziliexCurrencyStub> stubs = BraziliexUtils.listCurrencyStubFromResponse(listCurrency);
				for (BraziliexCurrencyStub st : stubs) {
					if (st != null && StringUtils.isNotBlank(st.getName())
							&& !Symbol.getFromName(st.getName()).equals(Symbol.UNKNOWN)
							&& !BraziliexUtils.currencyExists(st, currencies)) {
						Currency cr = BraziliexUtils.getCurrencyFromStub(st);
						currencyRepo.save(cr);
					}
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	@Scheduled(cron = "0 0/5 * * * *")
	public void setupTickers() {
		Exchange braziliex = exchangeRepo.findOneByName(BRAZILIEX);
		if (braziliex == null) {
			return;
		}
		List<Market> markets = marketRepo.findByExchange(braziliex);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -2);
		try {
			int saved = 0;
			CoinTradeBasicRequest rsp = CoinTradeUtils.executeGetRequest(URL_TICKERS);
			if (rsp.getHttpResponseCode() != HttpStatus.SC_OK) {
				return;
			}
			Gson gson = CoinTradeUtils.createGson();
			BraziliexListTickerStub listTicker = gson.fromJson(rsp.getResponseContent(), BraziliexListTickerStub.class);
			List<BraziliexTickerStub> stubs = BraziliexUtils.listTickerStubFromResponse(listTicker);
			for (BraziliexTickerStub st : stubs) {
				if (StringUtils.isBlank(st.getMarket())) {
					continue;
				}
				Market mkt = BraziliexUtils.getMarketBySymbols(st.getMarket(), markets);
				if (mkt == null) {
					String[] crs = StringUtils.split(st.getMarket(), "_");
					Symbol refSymbol = Symbol.getFromSymbol(crs[1]);
					Symbol changeSymbol = Symbol.getFromSymbol(crs[0]);
					if (refSymbol.equals(Symbol.UNKNOWN) || changeSymbol.equals(Symbol.UNKNOWN)) {
						continue;
					}
					Currency refCoin = currencyRepo.findOneByName(refSymbol);
					Currency changeCoin = currencyRepo.findOneByName(changeSymbol);
					if (refCoin == null || changeCoin == null) {
						continue;
					}
					mkt = new Market();
					mkt.setActive(true);
					mkt.setExchange(braziliex);
					mkt.setName(changeSymbol.getSymbol().toUpperCase().concat(refSymbol.getSymbol().toUpperCase()));
					mkt.setReferenceCoin(refCoin);
					mkt.setChangeCoin(changeCoin);
					mkt.setTrace(Boolean.TRUE);
					marketRepo.save(mkt);
				}
				Ticker tk = BraziliexUtils.getTickerFromStub(braziliex, mkt, st);
				tickerRepo.save(tk);
				saved++;
			}
			log.info("Braziliex tickers saved [{} tkt]", saved);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}
