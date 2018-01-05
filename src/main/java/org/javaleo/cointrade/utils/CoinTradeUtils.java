package org.javaleo.cointrade.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.javaleo.cointrade.responses.CoinTradeBasicResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CoinTradeUtils {

	public static final String ACCEPT = "Accept";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String JSON_APPLICATION = "application/json; charset=UTF-8";

	public static CoinTradeBasicResponse executeGetRequest(String url) throws IOException {
		CoinTradeBasicResponse coinTradeResponse = new CoinTradeBasicResponse();
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

}
