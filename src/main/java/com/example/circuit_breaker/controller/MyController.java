package com.example.circuit_breaker.controller;

import com.example.circuit_breaker.service1.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/test")
    public String testCircuitBreaker() {
        return myService.externalApiCall();
    }
}
