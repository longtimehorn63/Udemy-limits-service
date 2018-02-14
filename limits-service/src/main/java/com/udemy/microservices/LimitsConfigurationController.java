package com.udemy.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.udemy.microservices.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration(){
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
		
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="retrieveFallbackConfiguration")
	public LimitConfiguration retrieveConfiguration(){
		throw new RuntimeException("Not Avaliable");
	}
	

	public LimitConfiguration retrieveFallbackConfiguration(){
		return new LimitConfiguration(9,999);
	}
}
