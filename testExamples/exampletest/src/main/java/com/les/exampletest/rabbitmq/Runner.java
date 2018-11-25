package com.les.exampletest.rabbitmq;

import com.les.exampletest.ExampletestApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: exampletest
 * @Package: com.les.exampletest.rabbitmq
 * @ClassName: Runner
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/16 14:18
 * @UpdateUser: king
 * @UpdateDate: 2018/11/16 14:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

 /*
@Component
@Slf4j
public class Runner implements CommandLineRunner {


    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;


    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Sending message...");

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < MessageQueueConfig.testNumber; i++) {
            rabbitTemplate.convertAndSend(MessageQueueConfig.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ - message ! " + i);
        }

        receiver.getLatch().await();
        log.info("total used time =="  + (System.currentTimeMillis() - startTime) + " ms");

    }



}

    */