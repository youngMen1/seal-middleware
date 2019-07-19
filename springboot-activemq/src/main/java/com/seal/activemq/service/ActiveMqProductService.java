package com.seal.activemq.service;

import javax.jms.Destination;

public interface ActiveMqProductService {

    /**
     * 发送消息
     *
     * @param queueName
     * @param message
     */
    void sendMessage(String queueName, String message);


    /**
     * 发送消息
     * @param destination
     * @param message
     */
    void sendMessage(Destination destination, String message);
}
