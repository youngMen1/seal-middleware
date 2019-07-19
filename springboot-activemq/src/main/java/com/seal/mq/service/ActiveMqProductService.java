package com.seal.mq.service;

import javax.jms.Destination;

public interface ActiveMqProductService {

    /**
     * 发送消息
     *
     * @param destination
     * @param message
     */
    void sendMessage(String destination, String message);


    /**
     * 发送消息
     * @param destination
     * @param message
     */
    void sendMessage(Destination destination, String message);
}
