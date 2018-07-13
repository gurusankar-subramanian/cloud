package com.demo.eurekaclient1.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/api/service/")
@RefreshScope
public class EurekaController {

	@Autowired 
	RestTemplate restTemplate;

	 @Value("${client1.testValue}")
	 String testValue;
	
    @GetMapping("/names")
    @HystrixCommand(fallbackMethod = "defaultMethod")
    public List<String> getUserName(){ 
       ResponseEntity<List<String>> result = restTemplate.exchange("http://client2/api/service/names", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {});

        return result.getBody(); 
    }

    @GetMapping("/testValue")
    public String getTestValue(){ 
        return testValue; 
    }
    
    public List<String> defaultMethod(){
    	return Arrays.asList( "Hystrix Default Method");
    }
}
