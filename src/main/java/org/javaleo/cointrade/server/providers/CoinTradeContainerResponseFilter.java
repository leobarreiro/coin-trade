package org.javaleo.cointrade.server.providers;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Provider
public class CoinTradeContainerResponseFilter implements ContainerResponseFilter {

	@Autowired
	private CoinTradeSingletonBean cointradeSingletonBean;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		cointradeSingletonBean.amIAlive();

		// TODO fazer um filtro de autorizacao aqui.
		if (requestContext.getHeaderString("ping") != null) {
			responseContext.getHeaders().add("pong", "pong");
		}

	}

}
