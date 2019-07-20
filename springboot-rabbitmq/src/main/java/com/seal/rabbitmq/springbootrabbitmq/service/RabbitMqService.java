package com.seal.rabbitmq.springbootrabbitmq.service;


public interface RabbitMqService {

    /**
     * 发送消息
     *
     * @param message
     */
    void sendMessage(String message);
}
