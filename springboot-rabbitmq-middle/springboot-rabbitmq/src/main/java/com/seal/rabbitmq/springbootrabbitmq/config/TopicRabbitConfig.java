package com.seal.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:34
 * @description 主题模式(topic)
 **/
@Configuration
public class TopicRabbitConfig {
    /**
     * 两个队列名
     */
    public final static String TOPIC_A = "topic_A";
    public final static String TOPIC_B = "topic_B";

    /**
     * 声明一个Topic类型的交换机
     */
    public static final String TOPIC_EXCHANGE_NAME = "topic.exchange";

    /**
     * 声明一个路由关键字
     */
    public static final String TOPIC_ROUTING_KEY = "topic.routingKey";

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.TOPIC_A);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.TOPIC_B);
    }

    /**
     * 声明一个Topic类型的交换机
     *
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    /**
     * 绑定Q队列到交换机,并且指定routingKey
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(TOPIC_ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
