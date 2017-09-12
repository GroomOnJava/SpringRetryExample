package com.spring.retry.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryService {

	private static final Logger LOGGER = LogManager.getLogger(RetryService.class);

	@Retryable(include=NullPointerException.class, backoff = @Backoff(delay=3000, maxDelay=3001), maxAttempts = 5)
	public void execute(String foo) {
		LOGGER.info(foo);
		try{
			if ("foo".equals(foo)) {
				throw new NullPointerException();
			}else{
				throw new IllegalStateException();
			}
		}catch(NullPointerException e){
			throw(e);
		}catch(IllegalStateException e){
			throw(e);
		}
	}

	@Recover
	public void connectionException(NullPointerException e) {
		LOGGER.info("NullPointer Retry failure");
	}

	@Recover
	public void connectionException(Exception e) throws Exception {
		LOGGER.info("IllegalState Retry failure");
		throw e;
	}

}
