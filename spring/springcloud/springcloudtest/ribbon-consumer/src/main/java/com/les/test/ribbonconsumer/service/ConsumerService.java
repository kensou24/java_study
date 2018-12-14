package com.les.test.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: ConsumerService
 * @Description:
 * @Author: king
 * @CreateDate: 2018/12/11 11:56
 */
@Service
@Slf4j
public class ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloConsumerError")
    public String helloConsumer()
    {
        Long starTime = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://HELLO-CLIENT/hello", String.class).getBody().toString();
        log.info("used time ==" + (System.currentTimeMillis() - starTime));
        return result;
    }

    public String helloConsumerError()
    {
        return "not avaliable now, please wait ...";
    }

}
