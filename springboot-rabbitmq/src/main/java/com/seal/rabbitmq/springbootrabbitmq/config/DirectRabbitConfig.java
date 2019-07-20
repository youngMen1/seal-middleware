package com.seal.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 路由模式(routing/direct)
 **/
@Configuration
public class DirectRabbitConfig {

    /**
     * 两个队列名
     */
    public final static String DIRECT_A = "direct_A";
    public final static String DIRECT_B = "direct_B";
    public final static String DIRECT_C = "direct_C";

    /**
     * 声明一个Topic类型的交换机
     */
    public static final String DIRECT_EXCHANGE_NAME = "direct.exchange";

    /**
     * 声明一个路由关键字
     */
    public static final String DIRECT_ROUTING_KEY = "direct.routingKey";

    @Bean
    public Queue q_direct_A() {
        return new Queue(DIRECT_A);
    }

    @Bean
    public Queue q_direct_B() {
        return new Queue(DIRECT_B);
    }

    @Bean
    public Queue q_direct_C() {
        return new Queue(DIRECT_C);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    Binding bindingExchangeDA(Queue q_direct_A, DirectExchange directExchange) {
        return BindingBuilder.bind(q_direct_A).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangeDB(Queue q_direct_B, DirectExchange directExchange) {
        return BindingBuilder.bind(q_direct_B).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding bindingExchangeDC(Queue q_direct_C, DirectExchange directExchange) {
        return BindingBuilder.bind(q_direct_C).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

}
