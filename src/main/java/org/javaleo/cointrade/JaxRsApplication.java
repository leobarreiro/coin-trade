package org.javaleo.cointrade;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/")
public class JaxRsApplication extends Application {

}
