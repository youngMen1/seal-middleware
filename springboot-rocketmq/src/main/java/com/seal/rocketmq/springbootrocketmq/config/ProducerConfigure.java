package com.seal.rocketmq.springbootrocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProducerConfigure {


    @Autowired
    private ProducerConfig producerConfigure;

    /**
     * 创建普通消息发送者实例
     *
     * @return
     * @throws MQClientException
     */
    @Bean
    // @ConditionalOnProperty(prefix = "rocketmq.producer", value = "default", havingValue = "true")
    public DefaultMQProducer defaultProducer() {
        log.info(producerConfigure.toString());
        log.info("defaultProducer 正在创建---------------------------------------");
        DefaultMQProducer producer = new DefaultMQProducer(producerConfigure.getGroupName());
        producer.setNamesrvAddr(producerConfigure.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        log.info("rocketmq producer server开启成功---------------------------------");
        return producer;
    }
}