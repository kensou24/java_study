package com.les.test.eurekaclient.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @ClassName: HelloService
 * @Description:
 * @Author: king
 * @CreateDate: 2018/12/11 9:13
 */
@RestController
@Slf4j
public class HelloService {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping
    public String hello() throws InterruptedException {
        ServiceInstance instance = client.getLocalServiceInstance();

        int sleepTime = new Random().nextInt(500);
        log.info("prepare to sleep ... " + sleepTime);
        //Thread.sleep( sleepTime);

        log.info("local service info " + instance.getHost() + ",id=" + instance.getServiceId());
        return "hello world";
    }
}
