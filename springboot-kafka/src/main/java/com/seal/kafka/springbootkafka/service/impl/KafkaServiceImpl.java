package com.seal.kafka.springbootkafka.service.impl;

import com.seal.kafka.springbootkafka.config.KafkaConfig;
import com.seal.kafka.springbootkafka.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 16:16
 * @description 生产者
 **/
@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendService(String message) {
        kafkaTemplate.send(KafkaConfig.TEST_QUEUE, message);
    }
}
