package com.mybank.mscuentamovimiento.infrastrcuture.feign;

import feign.Request;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientFeignConfig {
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000); // timeout
    }

    @Bean
    public CircuitBreaker circuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("clientValidation");
    }

    @Bean
    public Retry retry(RetryRegistry registry) {
        return registry.retry("clientValidation");
    }
}
