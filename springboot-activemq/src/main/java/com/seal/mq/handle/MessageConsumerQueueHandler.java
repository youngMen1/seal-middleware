package com.seal.mq.handle;

import com.seal.mq.config.ActiveMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 14:22
 * @description 消费者-队列模式
 **/
@Slf4j
@Component
public class MessageConsumerQueueHandler {

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     *
     * @param message
     */
    @JmsListener(destination = ActiveMqConfig.JMS_QUEUE)
    public void receiveQueue(String message) {
        // 消息内容转为具体对象，数据类型更明晰
        log.info("====" + message);
    }
}
