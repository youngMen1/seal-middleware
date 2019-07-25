package com.seal.kafka.springbootkafka.handler;

import com.alibaba.fastjson.JSON;
import com.seal.kafka.springbootkafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/25 14:55
 * @description 消费者
 **/
@Slf4j
@Component
public class KafkaReceiver {

    @KafkaListener(topics = {KafkaConfig.TEST_QUEUE})
    public void listen(String message) {
        log.info("------------------接收消息 message =" + message);
        Message msg = JSON.parseObject(message, Message.class);
        log.info("MessageConsumer: onMessage: message is: [" + msg + "]");
        log.info("------------------ message =" + message);
    }
}
