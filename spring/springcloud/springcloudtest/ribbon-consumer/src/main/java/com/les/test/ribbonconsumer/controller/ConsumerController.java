package com.les.test.ribbonconsumer.controller;

import com.les.test.ribbonconsumer.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: ConsumerController
 * @Description:
 * @Author: king
 * @CreateDate: 2018/12/11 10:29
 */
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping
    public String helloConsumer()
    {
        return consumerService.helloConsumer();
    }


}
