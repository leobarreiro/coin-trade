package org.javaleo.cointrade.server.providers;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Provider
public class CoinTradeContainerResponseFilter implements ContainerResponseFilter {

	private static final String AUTHORIZATION = "Authorization";

	private Logger LOG = LoggerFactory.getLogger(CoinTradeContainerResponseFilter.class);

	@Autowired
	private CoinTradeSingletonBean cointradeSingletonBean;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		cointradeSingletonBean.amIAlive();

		// TODO fazer um filtro de autorizacao aqui.
		if (requestContext.getHeaderString(AUTHORIZATION) != null) {
			String auth = requestContext.getHeaderString(AUTHORIZATION);
			String[] authParts = StringUtils.split(auth, ":");
			String userId = authParts[0];
			String signature = authParts[1];
		}

		// responseContext.setStatus(402);

		String passwd = "secret@123";
		String message = "Tomar mate é muito bom, thcê.";

		try {
			String secret = cointradeSingletonBean.generateHashToUser(passwd);
			// LOG.info(secret);
			String signature = cointradeSingletonBean.macToolsEncrypt(secret, message);
			// LOG.info("Signature: {}", signature);
			boolean verified = cointradeSingletonBean.verifySignature(secret, signature, message);
			if (verified) {
				// LOG.info("Mensagem validada.");
			} else {
				// LOG.info("Mensagem nao validada.");
			}
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage());
		} catch (InvalidKeyException | DecoderException e) {
			LOG.error(e.getMessage());
		}

	}

}
