package com.seal.activemq.handle;

import com.seal.activemq.config.ActiveMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 14:29
 * @description 消费者-订阅模式
 **/
@Slf4j
@Component
public class MessageConsumerTopicHandler {

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     *
     * @param message
     */
    @JmsListener(destination = ActiveMqConfig.JMS_TOPIC)
    public void receiveQueue(String message) {
        // 消息内容转为具体对象，数据类型更明晰
        log.info("====" + message);
    }
}
