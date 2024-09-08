package com.cesar31.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SaTransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaTransactionServiceApplication.class, args);
    }
}
