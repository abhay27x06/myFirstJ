package com.springT.First.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public String HealthCheck(HttpServletRequest request)
    {
        return "sab theek hai yahan"+request.getSession().getId();
    }
    @GetMapping("/about")
    public String AboutCheck(HttpServletRequest request)
    {
        return "about bhi theek hi hai"+request.getSession().getId();
    }
}
