package com.seal.rocketmq.springbootrocketmq.rocketmq.consumer.processor;

import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 消息队列-消息消费处理接口
 **/
public interface MQMsgProcessor {
    /**
     * 消息处理<br/>
     * 如果没有return true ，consumer会重新消费该消息，直到return true<br/>
     * consumer可能重复消费该消息，请在业务端自己做是否重复调用处理，该接口设计为幂等接口
     *
     * @param topic 消息主题
     * @param tag   消息标签
     * @param msgs  消息
     * @return 2018年3月1日 zhaowg
     */
    MQConsumeResult handle(String topic, String tag, List<MessageExt> msgs);
}
