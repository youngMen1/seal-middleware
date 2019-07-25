package com.seal.kafka.springbootkafka.service;

import com.alibaba.fastjson.JSON;
import com.seal.kafka.springbootkafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/25 14:34
 * @description 生产者(泛型类)
 **/
@Slf4j
@Component
public class KafkaSender<T> {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送消息方法
     *
     * @param obj
     */
    public void send(T obj) {
        String jsonObj = JSON.toJSONString(obj);
        // 发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaConfig.TEST_QUEUE, jsonObj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // 失败消息未能发送
                log.info("Produce: The message failed to be sent:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //TODO 业务处理
                log.info("Produce: The message was sent successfully:");
                log.info("Produce: _+_+_+_+_+_+_+ result: " + stringObjectSendResult.toString());
            }
        });
    }


}
