package com.seal.rabbitmq.springbootrabbitmq.handle;

import com.rabbitmq.client.Channel;
import com.seal.rabbitmq.springbootrabbitmq.config.QueueRabbitConfig;
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
 * @description work模式 消费者
 **/
@Slf4j
@Component
public class RabbitConsumerWorkService {

    @RabbitListener(queues = QueueRabbitConfig.QUEUE_NAME, errorHandler = "conditionRabbitListenerErrorHandler")
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
}
