package com.cic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class MqPlatfromApplicationRunner implements ApplicationRunner {

	
	private static final Logger logger = LoggerFactory.getLogger(MqPlatfromApplicationRunner.class);

	
	
    @Override  
    public void run(ApplicationArguments args) throws Exception {
    	logger.info("程序启动开始...");
    } 
    
} 