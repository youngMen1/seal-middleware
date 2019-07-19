package com.seal.activemq.service.impl;

import com.seal.activemq.service.ActiveMqProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 10:25
 * @description 消费生产者
 **/
@Slf4j
@Service
public class ActiveMqProductServiceImpl implements ActiveMqProductService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(String queueName, String message) {

        jmsMessagingTemplate.convertAndSend(queueName, message);

    }

    @Override
    public void sendMessage(Destination destination, String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

}
