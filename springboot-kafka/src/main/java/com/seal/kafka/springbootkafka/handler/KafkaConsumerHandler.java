package com.seal.kafka.springbootkafka.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 16:23
 * @description 消费者
 **/
@Component
public class KafkaConsumerHandler {

    @KafkaListener(topics = "test_topic")
    public void listen(ConsumerRecord<?, ?> record) {
        //控制台打印send进来的信息
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }


}
