package com.seal.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:43
 * @description 发布订阅模式
 **/
@Configuration
public class FanoutRabbitConfig {

    /**
     * 三个队列名
     */
    public final static String FANOUT_A = "fanout_A";
    public final static String FANOUT_B = "fanout_B";
    public final static String FANOUT_C = "fanout_C";

    /**
     * 声明一个Fanout类型的交换机
     */
    public static final String TOPIC_EXCHANGE_NAME = "fanout.exchange";

    /**
     * 声明一个路由关键字
     */
    public static final String FANOUT_ROUTING_KEY = "fanout.routingKey";


    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue aMessage() {
        return new Queue(FANOUT_A);
    }

    @Bean
    public Queue bMessage() {
        return new Queue(FANOUT_B);
    }

    @Bean
    public Queue cMessage() {
        return new Queue(FANOUT_C);
    }

    /**
     * 声明一个Fanout类型的交换机
     * @return
     */
    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(TOPIC_EXCHANGE_NAME);
    }

    /**
     * 绑定aMessage队列到交换机
     *
     * @param aMessage
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue aMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(aMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue bMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(bMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue cMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(cMessage).to(fanoutExchange);
    }

}
