package com.spring.retry.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.retry.service.RetryService;

@EnableRetry
@RestController
public class RetryController {

	private static final Logger LOGGER = LogManager.getLogger(RetryController.class);

	@Autowired
	public RetryService retryService;

	@RequestMapping("/")
	public void api() {
		try {
			retryService.execute("foo");
			LOGGER.info("Recovered");
		}
		catch (Exception e) {
			System.out.println("Not recovered: " + e);
		}
		try {
			retryService.execute("bar");
		}
		catch (Exception e) {
			LOGGER.info("Not recovered: " + e);
		}
	}

}
