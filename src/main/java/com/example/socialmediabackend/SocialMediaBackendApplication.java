package com.example.socialmediabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SocialMediaBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialMediaBackendApplication.class, args);
    }
}
