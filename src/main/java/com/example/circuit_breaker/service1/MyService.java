package com.example.circuit_breaker.service1;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

//    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackMethod")
    @Retry(name = "myService", fallbackMethod = "fallbackMethod")
    public String externalApiCall() {
        // Simulating an API call or any external service call that might fail
        logger.info("Attempting external service call...");
        String url = "http://localhost:8080/api/v1/data-result";
        return restTemplate.getForObject(url, String.class);
    }

    // Fallback method in case of failure
    public String fallbackMethod(Throwable throwable) {
        logger.info("Fallback invoked due to: {}", throwable.getMessage());
        return "Fallback response: External service is unavailable.";
    }
}
