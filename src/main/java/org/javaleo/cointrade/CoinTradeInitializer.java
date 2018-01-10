package org.javaleo.cointrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan({ "org.javaleo.cointrade.server.endpoints", "org.javaleo.cointrade.server.schedules", "org.javaleo.cointrade.server.providers" })
public class CoinTradeInitializer extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CoinTradeInitializer.class, args);
	}

	// @Bean
	// public ServletRegistrationBean servletRegistrationBean() {
	// FacesServlet servlet = new FacesServlet();
	// return new ServletRegistrationBean(servlet, "*.jsf");
	// }
	//
	// @Bean
	// public FilterRegistrationBean rewriteFilter() {
	// FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
	// rwFilter.setDispatcherTypes(
	// EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
	// rwFilter.addUrlPatterns("/*");
	// return rwFilter;
	// }

}
