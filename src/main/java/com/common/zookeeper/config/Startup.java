package com.common.zookeeper.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Startup {
	public static void main(String[] args) {
		
	    new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
		// develop
    }
}
