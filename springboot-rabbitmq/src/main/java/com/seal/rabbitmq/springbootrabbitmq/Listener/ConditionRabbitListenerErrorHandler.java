package com.seal.rabbitmq.springbootrabbitmq.Listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/18 10:22
 * @description
 **/
@Component
@Slf4j
public class ConditionRabbitListenerErrorHandler implements RabbitListenerErrorHandler {

    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) {
        log.error("RabbitMQ execute message->{} failed，cause->{}",amqpMessage,exception.getCause());
        return new Object();
    }
}