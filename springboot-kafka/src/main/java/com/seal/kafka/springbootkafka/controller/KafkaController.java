package com.seal.kafka.springbootkafka.controller;

import com.seal.kafka.springbootkafka.service.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/25 14:50
 * @description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/information")
public class KafkaController {

    @Autowired
    private KafkaSender sender;

    @PostMapping(value = "/send")
    public String sendKafka(String message) {
        try {
            log.info("kafka的消息={}", message);
            sender.send(message);
            log.info("发送kafka成功.");
            return "SUCCESS";
        } catch (Exception e) {
            log.error("发送kafka失败", e);
            return "FAIL";
        }
    }

}
