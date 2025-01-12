package com.springT.First.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/ok")
    public String HealthCheck()
    {
        return "sab theek hai yahan";
    }
}
