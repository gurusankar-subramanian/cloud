package com.demo.eurekaclient2.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("api/service/")
public class EurekaController {
 
	 @Value("${client2.testValue}")
	 String testValue;
	
    @GetMapping("/names")
    public List<String> getUserName(){ 
    	return Arrays.asList("1","2");
    }


    @GetMapping("/testValue")
    public String getConfigValue(){ 
    	return testValue;
    }

}
