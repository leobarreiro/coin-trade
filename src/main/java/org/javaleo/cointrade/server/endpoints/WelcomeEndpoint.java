package org.javaleo.cointrade.server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/welcome")
public class WelcomeEndpoint {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String welcomeMessage() {
		return "Callate la boca sorete.";
	}

}
