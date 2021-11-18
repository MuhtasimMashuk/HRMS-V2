package com.ibcs.tnl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class TnlApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(TnlApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TnlApplication.class, args);
		LOGGER.debug("This is a debug message");
		LOGGER.info("This is an info message");
		LOGGER.warn("This is a warn message");
		LOGGER.error("This is an error message");
	}

}
