package com.seal.rocketmq.springbootrocketmq.rocketmq.annotation;

import com.seal.rocketmq.springbootrocketmq.rocketmq.TopicEnum;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 此注解用于标注消费者服务
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Service
public @interface MQConsumeService {
    /**
     * 消息主题
     */
     TopicEnum topic();

    /**
     * 消息标签,如果是该主题下所有的标签，使用“*”
     */
     String[] tags();


}
