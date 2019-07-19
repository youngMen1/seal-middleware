package com.seal.rocketmq.springbootrocketmq.service;

public interface RocketMqService {

    /**
     * 发送消息
     *
     * @param message
     */
    void sendMessage(String message);
}
