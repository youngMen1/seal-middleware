package com.seal.rabbitmq.springbootrabbitmq.handle;

import com.rabbitmq.client.Channel;
import com.seal.rabbitmq.springbootrabbitmq.config.DirectRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/11/13 13:55
 * @description 路由模式(routing / direct) 消费者
 * Direct：定向，把消息交给符合指定routing key 的队列
 **/
@Slf4j
@Component
public class RabbitConsumeDirectService {

    @RabbitListener(queues = DirectRabbitConfig.DIRECT_A, errorHandler = "conditionRabbitListenerErrorHandler")
    public void receiveSearchConditionRequest(String message, Channel channel,
                                              @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {

            log.info("==============" + message);


            // 消息确认,false表示使用手动确认模式
            channel.basicAck(tag, false);
        } catch (IOException e) {
            channel.basicNack(tag, false, false);
        }
    }


    @RabbitListener(queues = DirectRabbitConfig.DIRECT_B, errorHandler = "conditionRabbitListenerErrorHandler")
    public void receiveSearchConditionRequest2(String message, Channel channel,
                                               @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {

            log.info("==============" + message);


            // 消息确认,false表示使用手动确认模式
            channel.basicAck(tag, false);
        } catch (IOException e) {
            channel.basicNack(tag, false, false);
        }
    }


    @RabbitListener(queues = DirectRabbitConfig.DIRECT_C, errorHandler = "conditionRabbitListenerErrorHandler")
    public void receiveSearchConditionRequest3(String message, Channel channel,
                                               @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {

            log.info("==============" + message);


            // 消息确认,false表示使用手动确认模式
            channel.basicAck(tag, false);
        } catch (IOException e) {
            channel.basicNack(tag, false, false);
        }
    }


}
