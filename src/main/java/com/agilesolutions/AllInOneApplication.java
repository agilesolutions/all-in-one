package com.agilesolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class AllInOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(AllInOneApplication.class, args);
    }
}
