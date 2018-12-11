package com.les.test.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @ClassName: TrubineApp
 * @Description:
 * @Author: king
 * @CreateDate: 2018/12/11 13:45
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class TrubineApp {

    public static void main(String[] args) {
        SpringApplication.run(TrubineApp.class, args);
    }
}
