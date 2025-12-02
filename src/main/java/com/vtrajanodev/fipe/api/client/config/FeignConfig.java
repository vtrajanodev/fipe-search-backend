package com.vtrajanodev.fipe.api.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
        basePackages = "com.vtrajanodev.fipe.api.client.client"
)
public class FeignConfig {
}