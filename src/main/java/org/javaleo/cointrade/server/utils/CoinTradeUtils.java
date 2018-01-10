package org.javaleo.cointrade.server.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.javaleo.cointrade.server.requests.CoinTradeBasicRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CoinTradeUtils {

	public static final String ACCEPT = "Accept";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String JSON_APPLICATION = "application/json; charset=UTF-8";

	public static CoinTradeBasicRequest executeGetRequest(String url) throws IOException {
		CoinTradeBasicRequest coinTradeResponse = new CoinTradeBasicRequest();
		CloseableHttpClient httpClient = HttpClients.createMinimal();
		HttpGet httpGet = new HttpGet(url);
		prepareAuthorization(httpGet);
		HttpResponse response = httpClient.execute(httpGet);
		coinTradeResponse.setHttpProtocol(response.getStatusLine().getProtocolVersion().getProtocol());
		coinTradeResponse.setHttpResponseCode(response.getStatusLine().getStatusCode());
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer content = new StringBuffer();
		String input = "";
		while ((input = buffReader.readLine()) != null) {
			content.append(input);
		}
		coinTradeResponse.setResponseContent(content.toString());
		httpClient.close();
		return coinTradeResponse;
	}

	private static void prepareAuthorization(HttpMessage httpMessage) {
		httpMessage.addHeader(ACCEPT, JSON_APPLICATION);
		httpMessage.addHeader(CONTENT_TYPE, JSON_APPLICATION);
	}

	public static Gson createGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		return gsonBuilder.create();
	}

	public static long now() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		return c.getTimeInMillis();
	}

	public static long oneHourAgo() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.add(Calendar.MINUTE, -60);
		return c.getTimeInMillis();
	}

	public static long fiveMinutesAgo() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.add(Calendar.MINUTE, -5);
		return c.getTimeInMillis();
	}

	public static long tenMinutesAgo() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.add(Calendar.MINUTE, -10);
		return c.getTimeInMillis();
	}

	public static Long halfHourAgo() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.add(Calendar.MINUTE, -30);
		return c.getTimeInMillis();
	}

	public static long aDayAgo() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTimeInMillis();
	}

	public static int thisYear() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		return c.get(Calendar.YEAR);
	}

	public static int thisMonth() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		return (c.get(Calendar.MONTH) + 1);
	}

	public static int thisDay() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int thisHour() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static int thisMinute() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		return c.get(Calendar.MINUTE);
	}

	public static Double getLowest(List<Double> averageList) {
		Double lowest = null;
		if (averageList.isEmpty()) {
			return 0.00;
		}
		for (Double db : averageList) {
			if (lowest == null) {
				lowest = db;
			}
			if (lowest > db) {
				lowest = db;
			}
		}
		return lowest;
	}

	public static Double getHighest(List<Double> averageList) {
		Double highest = null;
		if (averageList.isEmpty()) {
			return 0.00;
		}
		for (Double db : averageList) {
			if (highest == null) {
				highest = db;
			}
			if (highest < db) {
				highest = db;
			}
		}
		return highest;
	}

	public static Double getAverage(List<Double> averageList) {
		Double average = 0.00;
		if (averageList.isEmpty()) {
			return average;
		}
		for (Double db : averageList) {
			average += db;
		}
		return (average / averageList.size());
	}

}
