package com.vtrajanodev.fipe.api.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FipeFeignInterceptor {

  @Value("${fipe.api.token}")
  private String token;

  @Bean
  public RequestInterceptor fipeRequestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate template) {
        template.header("X-Subscription-Token", token);
      }
    };
  }
}
