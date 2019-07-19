package com.seal.rocketmq.springbootrocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.seal.rocketmq.springbootrocketmq.service.RocketMqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/19 15:23
 * @description
 **/
@Slf4j
@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Autowired
    private DefaultMQProducer producer;

    @Override
    public void sendMessage(String msg) {
        Message message = new Message();
        message.setKeys("12345");
        message.setTopic("TopicTest");
        message.setTags("Tag1");
        message.setBody("rocketmq测试成功".getBytes());
        try {
            // 这里用到了这个mq的异步处理，类似ajax，可以得到发送到mq的情况，并做相应的处理不过要注意的是这个是异步的
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("入队成功:{}" + JSON.toJSONString(sendResult));
                }

                @Override
                public void onException(Throwable e) {
                    log.error("入队失败:{}", e);
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
