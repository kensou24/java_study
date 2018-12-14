package com.les.test.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientrApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientrApplication.class, args);
    }
}
