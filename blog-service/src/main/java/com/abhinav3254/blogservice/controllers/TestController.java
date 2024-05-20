package com.abhinav3254.blogservice.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog-service")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<?> testServer() {
        return ResponseEntity.status(200).body("Test done");
    }
}
