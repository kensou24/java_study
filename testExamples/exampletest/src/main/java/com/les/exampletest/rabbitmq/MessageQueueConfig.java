package com.les.exampletest.rabbitmq;

import com.les.exampletest.ExampletestApplication;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

/**
 * @ProjectName: exampletest
 * @Package: com.les.exampletest.rabbitmq
 * @ClassName: MessageQueueConfig
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/16 14:32
 * @UpdateUser: king
 * @UpdateDate: 2018/11/16 14:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
public class MessageQueueConfig {
    public static final String topicExchangeName = "spring-boot-exchange";

    public static final String queueName = "spring-boot-" + UUID.randomUUID().toString().replace("-", "").toLowerCase();;

    public static final int testNumber = 100000;

    @Bean
    Queue queue() {
        return new Queue(MessageQueueConfig.queueName , false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(MessageQueueConfig.topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MessageQueueConfig.queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
