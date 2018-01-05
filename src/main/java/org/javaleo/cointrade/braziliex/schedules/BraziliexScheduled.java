package org.javaleo.cointrade.braziliex.schedules;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.javaleo.cointrade.braziliex.stubs.BrzCurrencyStub;
import org.javaleo.cointrade.braziliex.stubs.BrzListCurrencyStub;
import org.javaleo.cointrade.braziliex.stubs.BrzListTickerStub;
import org.javaleo.cointrade.braziliex.stubs.BrzTickerStub;
import org.javaleo.cointrade.braziliex.utils.BraziliexUtils;
import org.javaleo.cointrade.entities.Currency;
import org.javaleo.cointrade.entities.Exchange;
import org.javaleo.cointrade.entities.Market;
import org.javaleo.cointrade.entities.Ticker;
import org.javaleo.cointrade.enums.Symbol;
import org.javaleo.cointrade.repositories.CurrencyRepository;
import org.javaleo.cointrade.repositories.ExchangeRepository;
import org.javaleo.cointrade.repositories.MarketRepository;
import org.javaleo.cointrade.repositories.TickerRepository;
import org.javaleo.cointrade.responses.CoinTradeBasicResponse;
import org.javaleo.cointrade.utils.CoinTradeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class BraziliexScheduled {

	private static final String BRAZILIEX_URL = "https://braziliex.com";
	private static final String BRAZILIEX = "Braziliex";
	private static final Logger LOG = LoggerFactory.getLogger(BraziliexScheduled.class);
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");

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
		Exchange exch = exchangeRepo.findOneByName(BRAZILIEX);
		if (exch == null) {
			exch = new Exchange();
			exch.setName(BRAZILIEX);
			exch.setUrl(BRAZILIEX_URL);
			exchangeRepo.save(exch);
		}
	}

	@Scheduled(fixedDelay = 300000)
	public void getCurrencies() {
		List<Currency> currencies = currencyRepo.findAll();

		try {
			CoinTradeBasicResponse rsp = CoinTradeUtils.executeGetRequest(URL_CURRENCIES);
			if (rsp.getHttpResponseCode() == HttpStatus.SC_OK) {
				Gson gson = CoinTradeUtils.createGson();
				BrzListCurrencyStub listCurrency = gson.fromJson(rsp.getResponseContent(), BrzListCurrencyStub.class);
				List<BrzCurrencyStub> stubs = BraziliexUtils.listCurrencyStubFromResponse(listCurrency);

				for (BrzCurrencyStub st : stubs) {
					if (st != null && StringUtils.isNotBlank(st.getName()) && !Symbol.getFromName(st.getName()).equals(Symbol.UNKNOWN) && !BraziliexUtils.currencyExists(st, currencies)) {
						Currency cr = BraziliexUtils.getCurrencyFromStub(st);
						currencyRepo.save(cr);
					}
				}

			}

		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		LOG.info("Braziliex Currencies {}", DTF.format(LocalDateTime.now()));
	}

	@Scheduled(fixedDelay = 60000)
	public void getTickers() {
		Exchange braziliex = exchangeRepo.findOneByName(BRAZILIEX);
		if (braziliex == null) {
			return;
		}

		List<Market> markets = marketRepo.findByExchange(braziliex);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -3);
		List<Ticker> recentTickerSaved = tickerRepo.findTickerByExchangeAndTimeReferenceGreaterThanEqual(braziliex, c.getTimeInMillis());

		try {
			CoinTradeBasicResponse rsp = CoinTradeUtils.executeGetRequest(URL_TICKERS);

			if (rsp.getHttpResponseCode() != HttpStatus.SC_OK) {
				return;
			}
			Gson gson = CoinTradeUtils.createGson();
			BrzListTickerStub listTicker = gson.fromJson(rsp.getResponseContent(), BrzListTickerStub.class);
			List<BrzTickerStub> stubs = BraziliexUtils.listTickerStubFromResponse(listTicker);

			for (BrzTickerStub st : stubs) {
				Market mkt = BraziliexUtils.getMarketBySymbols(st.getMarket(), markets);
				if (mkt == null) {
					String[] crs = StringUtils.split(st.getMarket(), "_");
					Symbol refSymbol = Symbol.getFromSymbol(crs[1]);
					Symbol changeSymbol = Symbol.getFromSymbol(crs[0]);
					if (refSymbol.equals(Symbol.UNKNOWN) || changeSymbol.equals(Symbol.UNKNOWN)) {
						continue;
					}

					Currency refCoin = currencyRepo.findOneBySymbol(refSymbol);
					Currency changeCoin = currencyRepo.findOneBySymbol(changeSymbol);
					if (refCoin == null || changeCoin == null) {
						continue;
					}

					mkt = new Market();
					mkt.setActive(true);
					mkt.setExchange(braziliex);
					mkt.setName(changeSymbol.getSymbol().toUpperCase().concat(refSymbol.getSymbol().toUpperCase()));
					mkt.setReferenceCoin(refCoin);
					mkt.setChangeCoin(changeCoin);
					marketRepo.save(mkt);
				}

				Ticker tk = BraziliexUtils.getTickerFromStub(braziliex, mkt, st);
				// TODO verificar se o ticker ja existe na base de dados
				tickerRepo.save(tk);
			}

			LOG.info("Braziliex Tickers size: {}", stubs.size());

		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		LOG.info("Braziliex Tickers {}", DTF.format(LocalDateTime.now()));

	}

}
