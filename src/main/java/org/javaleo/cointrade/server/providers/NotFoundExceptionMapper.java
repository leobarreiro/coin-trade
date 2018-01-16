package org.javaleo.cointrade.server.providers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.javaleo.cointrade.server.responses.ErrorResponse;
import org.javaleo.cointrade.server.utils.CoinTradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Autowired
	private CoinTradeSingletonBean singletonBean;

	@Override
	public Response toResponse(NotFoundException excp) {

		singletonBean.amIAlive();

		ErrorResponse error = new ErrorResponse();
		error.setSuccess(false);
		error.setErrorCode(Response.Status.NOT_FOUND.getStatusCode());
		error.setMessage(excp.getMessage());
		error.setTimeStamp(CoinTradeUtils.now());

		Response.ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND).entity(error);
		responseBuilder.type(MediaType.APPLICATION_JSON);
		return responseBuilder.build();
	}

}
