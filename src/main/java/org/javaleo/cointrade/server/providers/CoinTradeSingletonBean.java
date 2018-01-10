package org.javaleo.cointrade.server.providers;

import org.springframework.stereotype.Component;

@Component
public class CoinTradeSingletonBean {

	public boolean amIAlive() {
		return true;
	}

}
