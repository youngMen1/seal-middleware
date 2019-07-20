package com.seal.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/6 10:11
 * @description 队列名称
 **/
@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_NAME = "test.queue";

    public static final String TOPIC_EXCHANGE_NAME = "test.exchange";

    public static final String WECHAT_ROUTING_KEY = "test.routingKey";

    @Bean(name = "message")
    public Queue queueMessage() {
        return new Queue(QUEUE_NAME);
    }

    @Bean(name = "exchange")
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage,
                                          @Qualifier("exchange") TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(WECHAT_ROUTING_KEY);
    }
}
