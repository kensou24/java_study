package com.les.test.tracec.service;

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

    @RequestMapping("hello")
    public String hello()  {
        return "hello from c";
    }
    @RequestMapping("hello2")
    public String hello2()  {
        return "hello2 from c";
    }

}
