package com.skanda.servicea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-a")
public class HelloController {

    @GetMapping("/hello-user")
    public String helloUser() {
        return "Hello USER from Service A";
    }

    @GetMapping("/hello-admin")
    public String helloAdmin() {
        return "Hello ADMIN from Service A";
    }
}