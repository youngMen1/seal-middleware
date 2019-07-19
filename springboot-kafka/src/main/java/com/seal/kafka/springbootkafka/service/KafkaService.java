package com.seal.kafka.springbootkafka.service;

public interface KafkaService {

    /**
     * 发送消息
     *
     * @param message
     */
    void sendService(String message);
}
