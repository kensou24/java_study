package com.les.test.traceb.service;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Service;
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
    private RestTemplate restTemplate;

    @RequestMapping("hello")
    public String hello()  {
        return restTemplate.getForEntity("http://TRACE-C/hello", String.class).getBody();
    }

    @RequestMapping("hello2")
    public String hello2()  {
        String result1 =restTemplate.getForEntity("http://TRACE-C/hello", String.class).getBody().toString();
        String result2 =restTemplate.getForEntity("http://TRACE-C/hello2", String.class).getBody().toString();
        return result1 + " " + result2;
    }
}

