package com.les.exampletest.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

/**
 * @ProjectName: exampletest
 * @Package: com.les.exampletest.rabbitmq
 * @ClassName: TestService
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/16 14:47
 * @UpdateUser: king
 * @UpdateDate: 2018/11/16 14:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RestController
@Slf4j
public class TestService {
    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @Autowired
    private  Receiver receiver;

    @GetMapping("receive")
    public String receive()
    {
        log.info("Sending message...");
        receiver.resetLatch();
        long startTime = System.currentTimeMillis();
        try {
            receiver.getLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long costTime = (System.currentTimeMillis() - startTime);
        log.info("receive total used time =="  + costTime  + " ms");
        return "cost time " + costTime + " ms";
    }

    @GetMapping("send")
    public String send(@RequestParam("times") int times)
    {
        long startTime = System.currentTimeMillis();
        String uniqueMessage = "Hello from RabbitMQ - message ! Hello from RabbitMQ - message ! " +
                "Hello from RabbitMQ - message ! Hello from RabbitMQ - message " +
                "Hello from RabbitMQ - message ! Hello from RabbitMQ - message ! Hello from RabbitMQ - message !";
        StringBuffer message = new StringBuffer(uniqueMessage);

        IntStream.range(0, times).forEach( (i)->{ message.append(uniqueMessage);});

        for(int i = 0; i < MessageQueueConfig.testNumber; i++) {
              rabbitTemplate.convertAndSend(MessageQueueConfig.topicExchangeName, "foo.bar.baz", message.toString() + "i");
          }
        long costTime = (System.currentTimeMillis() - startTime);
        log.info("send total used time =="  + costTime  + " ms");
        return "cost time " + costTime + " ms";
    }
}
