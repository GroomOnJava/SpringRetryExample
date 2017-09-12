package com.spring.retry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.retry.controller.RetryController;
import com.spring.retry.service.RetryService;

@Configuration
public class AppConfig {

  @Bean
  public RetryService retryService() {
    return new RetryService();
  }
  
  @Bean
  public RetryController retryController() {
    return new RetryController();
  }
}
