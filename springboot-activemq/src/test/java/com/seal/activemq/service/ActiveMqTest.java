package com.seal.activemq.service;

import com.seal.activemq.config.ActiveMqConfig;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 14:33
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMqTest {

    @Autowired
    private ActiveMQQueue queue;
    @Autowired
    private ActiveMQTopic topic;


    @Autowired
    private ActiveMqProductService productService;

    @Test
    public void testJms() {
        String msgQueue = "send 黄金 ";
        for (int i = 0; i < 5; i++) {
            productService.sendMessage(ActiveMqConfig.JMS_QUEUE, msgQueue + i);
        }
        String msgTopic = "send 白银 ";
        for (int i = 0; i < 5; i++) {
            productService.sendMessage(ActiveMqConfig.JMS_TOPIC, msgTopic + i);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
    }


    @Test
    public void testJms2() {
        String msgQueue = "send 黄金 ";
        for (int i = 0; i < 5; i++) {
            productService.sendMessage(this.queue, msgQueue + i);
        }
        String msgTopic = "send 白银 ";
        for (int i = 0; i < 5; i++) {
            productService.sendMessage(this.topic, msgTopic + i);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
    }

}
