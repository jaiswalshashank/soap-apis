package com.shasha.soapapis.config;

import com.shasha.soapapis.dataacess.webservicesserver.NumberConversionSoapType;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableWs
public class ApplicationConfig extends WsConfigurerAdapter {


	@Bean
	public ServletRegistrationBean cxfServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/wsi/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public CallbackHandler usernameTokenCallback() {
		return new WebServiceConfig();
	}


	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus(LoggingFeature loggingFeature) {

		SpringBus cxfBus = new SpringBus();
		cxfBus.getFeatures().add(loggingFeature);

		return cxfBus;
	}

	@Bean
	public LoggingFeature loggingFeature() {

		LoggingFeature loggingFeature = new LoggingFeature();
		loggingFeature.setPrettyLogging(true);

		return loggingFeature;
	}

	@Bean
	public Endpoint endpoint(Bus bus, NumberConversionSoapType numberConversionSoapType) {

		EndpointImpl endpoint = new EndpointImpl(bus, numberConversionSoapType);
		endpoint.publish("/service/accounts");

		
		return getCxfEndPoint(endpoint);
	}

	private org.apache.cxf.endpoint.Endpoint getCxfEndPoint(EndpointImpl endpoint) {
		org.apache.cxf.endpoint.Endpoint cxfEndPoint = endpoint.getServer().getEndpoint();

		Map inProps = new HashMap<>();
		inProps.put(ConfigurationConstants.ACTION, ConfigurationConstants.USERNAME_TOKEN);
		inProps.put(ConfigurationConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		inProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, WebServiceConfig.class.getName());

		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
		cxfEndPoint.getInInterceptors().add(wssIn);
		return cxfEndPoint;
	}

}
