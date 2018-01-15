package org.javaleo.cointrade.server.schedules;

import java.util.ArrayList;
import java.util.List;

import org.javaleo.cointrade.server.entities.Candle;
import org.javaleo.cointrade.server.entities.Market;
import org.javaleo.cointrade.server.entities.Ticker;
import org.javaleo.cointrade.server.enums.CandleInterval;
import org.javaleo.cointrade.server.repositories.CandleRepository;
import org.javaleo.cointrade.server.repositories.MarketRepository;
import org.javaleo.cointrade.server.repositories.TickerRepository;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CoinTradeScheduled {

	private Logger LOG = LoggerFactory.getLogger(CoinTradeScheduled.class);

	@Autowired
	private MarketRepository marketRepo;

	@Autowired
	private TickerRepository tickerRepo;

	@Autowired
	private CandleRepository candleRepo;

	@Scheduled(initialDelay = 60000, fixedDelay = 300000)
	public void cleanOldTickers() {
		List<Ticker> oldTickerList = tickerRepo.findByTimeReferenceLessThanEqual(CoinTradeUtils.aDayAgo());
		int olds = oldTickerList.size();
		tickerRepo.deleteInBatch(oldTickerList);
		LOG.info("Old tickers removed [{} reg]", olds);
	}

	@Scheduled(cron = "0 0/5 * * * *")
	public void calculateFiveMinutesCandles() {
		List<Market> marketList = marketRepo.findByTraceTrue();
		CandleInterval itn = CandleInterval.MIN5;
		int saved = 0;
		for (Market mk : marketList) {
			Candle cdl = mountCandlesByMarketAndInterval(mk, itn);
			candleRepo.save(cdl);
			saved++;
		}
		LOG.info("Candles calculated [Tp:{}|Reg:{}]", itn.getDescription(), saved);
	}

	@Scheduled(cron = "20 0/10 * * * *")
	public void calculateTenMinutesCandles() {
		List<Market> marketList = marketRepo.findByTraceTrue();
		CandleInterval itn = CandleInterval.MIN10;
		int saved = 0;
		for (Market mk : marketList) {
			Candle cdl = mountCandlesByMarketAndInterval(mk, itn);
			candleRepo.save(cdl);
			saved++;
		}
		LOG.info("Candles calculated [Tp:{}|Reg:{}]", itn.getDescription(), saved);
	}

	@Scheduled(cron = "50 0/30 * * * *")
	public void calculate30MinutesCandles() {
		List<Market> marketList = marketRepo.findByTraceTrue();
		CandleInterval itn = CandleInterval.MIN30;
		int saved = 0;
		for (Market mk : marketList) {
			Candle cdl = mountCandlesByMarketAndInterval(mk, itn);
			candleRepo.save(cdl);
			saved++;
		}
		LOG.info("Candles calculated [Tp:{}|Reg:{}]", itn.getDescription(), saved);
	}

	// @Scheduled(cron = "0 0 0/1 * * *")
	// public void calculateAnHourCandles() {
	// List<Market> marketList = marketRepo.findByTraceTrue();
	// CandleInterval itn = CandleInterval.HOUR1;
	// int saved = 0;
	// for (Market mk : marketList) {
	// Candle cdl = mountCandlesByMarketAndInterval(mk, itn);
	// candleRepo.save(cdl);
	// saved++;
	// }
	// LOG.info("Candles calculated [Tp:{}|Reg:{}]", itn.getDescription(), saved);
	// LOG.info("One-hour Candles are not calculating yet. I passed here to say hello.");
	// }

	private Candle mountCandlesByMarketAndInterval(Market mkt, CandleInterval itn) {
		long sinceLimit = (CandleInterval.MIN5.equals(itn)) ? CoinTradeUtils.fiveMinutesAgo() : CoinTradeUtils.tenMinutesAgo();
		List<Ticker> tickerList = tickerRepo.findTickerByInterval(mkt.getExchange(), mkt, sinceLimit, CoinTradeUtils.now());
		List<Double> averageList = new ArrayList<>();
		List<Double> marketCapList = new ArrayList<>();
		List<Double> quoteCapList = new ArrayList<>();
		for (Ticker t : tickerList) {
			Double average = (t.getLowestAsk() + t.getHighestBid()) / 2;
			averageList.add(average);
			marketCapList.add(t.getMarketCap());
			quoteCapList.add(t.getCurrencyVolume());
		}
		Candle cdl = new Candle();
		cdl.setExchange(mkt.getExchange());
		cdl.setMarket(mkt);
		cdl.setOpening(averageList.get(0));
		cdl.setClosure(averageList.get(averageList.size() - 1));
		cdl.setHighest(CoinTradeUtils.getHighest(averageList));
		cdl.setLowest(CoinTradeUtils.getLowest(averageList));
		cdl.setInterval(itn);
		cdl.setYear(CoinTradeUtils.thisYear());
		cdl.setMonth(CoinTradeUtils.thisMonth());
		cdl.setDay(CoinTradeUtils.thisDay());
		cdl.setHour(CoinTradeUtils.thisHour());
		cdl.setMinute(CoinTradeUtils.thisMinute());
		cdl.setCollectedTime(CoinTradeUtils.now());
		cdl.setMarketCapAmount(CoinTradeUtils.getAverage(marketCapList));
		cdl.setCurrencyAmount(CoinTradeUtils.getAverage(quoteCapList));
		return cdl;
	}
}
