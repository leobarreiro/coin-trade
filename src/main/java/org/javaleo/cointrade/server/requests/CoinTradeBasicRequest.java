package org.javaleo.cointrade.server.requests;

import java.io.Serializable;

public class CoinTradeBasicRequest implements Serializable {

	private static final long serialVersionUID = -5107845295968792292L;

	private int httpResponseCode;
	private long timestamp;
	private String httpProtocol;
	private String httpResponseHeaders;
	private String responseContent;

	public int getHttpResponseCode() {
		return httpResponseCode;
	}

	public void setHttpResponseCode(int httpResponseCode) {
		this.httpResponseCode = httpResponseCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getHttpProtocol() {
		return httpProtocol;
	}

	public void setHttpProtocol(String httpProtocol) {
		this.httpProtocol = httpProtocol;
	}

	public String getHttpResponseHeaders() {
		return httpResponseHeaders;
	}

	public void setHttpResponseHeaders(String httpResponseHeaders) {
		this.httpResponseHeaders = httpResponseHeaders;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

}
