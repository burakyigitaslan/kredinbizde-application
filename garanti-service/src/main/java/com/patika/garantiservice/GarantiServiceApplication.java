package com.patika.garantiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GarantiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarantiServiceApplication.class, args);
    }

}
