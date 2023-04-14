package com.bilskik.onlineshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/get")
    public String getName() {
        return "hello";
    }
}
