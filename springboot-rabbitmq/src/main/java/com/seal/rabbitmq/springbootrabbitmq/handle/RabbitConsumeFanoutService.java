package com.seal.rabbitmq.springbootrabbitmq.handle;

import com.rabbitmq.client.Channel;
import com.seal.rabbitmq.springbootrabbitmq.config.FanoutRabbitConfig;
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
 * @description 发布订阅模式 消费者
 * Fanout：广播，将消息交给所有绑定到交换机的队列，每个消费者都会收到同一条消息
 **/
@Slf4j
@Component
public class RabbitConsumeFanoutService {

    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_A, errorHandler = "conditionRabbitListenerErrorHandler")
    public void process(String message, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {

            log.info("==============" + message);


            // 消息确认,false表示使用手动确认模式
            channel.basicAck(tag, false);
        } catch (IOException e) {
            channel.basicNack(tag, false, false);
        }
    }

    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_B, errorHandler = "conditionRabbitListenerErrorHandler")
    public void process2(String message, Channel channel,
                         @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {


            log.info("==============" + message);


            // 消息确认,false表示使用手动确认模式
            channel.basicAck(tag, false);
        } catch (IOException e) {
            channel.basicNack(tag, false, false);
        }
    }


    @RabbitListener(queues = FanoutRabbitConfig.FANOUT_C, errorHandler = "conditionRabbitListenerErrorHandler")
    public void process3(String message, Channel channel,
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
