package org.javaleo.cointrade.server.responses;

import java.io.Serializable;

public abstract class CoinTradeBasicResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract long getTimeStamp();

	public abstract Boolean getSuccess();

	public abstract String getMessage();
}
