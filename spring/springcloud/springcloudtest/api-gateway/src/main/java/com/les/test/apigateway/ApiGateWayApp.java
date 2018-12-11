package com.les.test.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName: ApiGateWayApp
 * @Description:
 * @Author: king
 * @CreateDate: 2018/12/11 14:20
 */
@EnableZuulProxy
@SpringCloudApplication
public class ApiGateWayApp {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApp.class, args);
    }
}
