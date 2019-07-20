package com.seal.rabbitmq.springbootrabbitmq.handle;

import com.rabbitmq.client.Channel;
import com.seal.rabbitmq.springbootrabbitmq.config.RabbitMqConfig;
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
 * @description 消费者
 **/
@Slf4j
@Component
public class RabbitConsumerService {

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME, errorHandler = "conditionRabbitListenerErrorHandler")
    public void receiveSearchConditionRequest(String message, Channel channel,
                                              @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {


            log.info("==============" + message);


            // 消息确认
            channel.basicAck(tag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
