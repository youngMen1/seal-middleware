package com.seal.kafka.springbootkafka.handler;

import com.seal.kafka.springbootkafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 16:23
 * @description 消费者
 * 注意@Component注解，不然扫描不到@KafkaListener
 **/
@Slf4j
@Component
public class KafkaConsumerHandler {

    @KafkaListener(topics = KafkaConfig.TEST_QUEUE)
    public void listen(ConsumerRecord<?, ?> record) {
        //控制台打印send进来的信息
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }


}
