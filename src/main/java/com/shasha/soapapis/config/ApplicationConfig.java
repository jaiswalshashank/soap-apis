/*
package com.shasha.soapapis.config;

import javax.xml.ws.Endpoint;

import com.shasha.soapapis.dataacess.webservicesserver.NumberConversionSoapType;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;

import java.util.Collections;
import java.util.List;


@Configuration
@EnableWs
public class ApplicationConfig extends WsConfigurerAdapter {

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors){
		interceptors.add(securityInterceptor());
	}

	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		securityInterceptor.setCallbackHandler(callbackHandler());
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return securityInterceptor;
	}

	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
		callbackHandler.setUsersMap(Collections.singletonMap("admin", "pwd123"));
		return callbackHandler;
	}

	@Bean
	public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
		return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
	}
	
	@Bean
	@Primary
	public DispatcherServletPath dispatcherServletPathProvider() {
	    return () -> "";
	}

	@Bean(name=Bus.DEFAULT_BUS_ID)
	public SpringBus springBus(LoggingFeature loggingFeature) {
		
		SpringBus cxfBus = new  SpringBus();
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
		
		return endpoint;
	}

}*/
