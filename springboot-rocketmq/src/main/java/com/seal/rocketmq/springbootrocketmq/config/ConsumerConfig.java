package com.seal.rocketmq.springbootrocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/9 18:30
 * @description 消费者配置
 **/
@Data
@Configuration
@ConfigurationProperties(ConsumerConfig.PREFIX)
public class ConsumerConfig {

    /**
     * 读取yml文件配置
     */
    public static final String PREFIX = "rocketmq.consumer";

    private String groupName;

    private String namesrvAddr;


    @Override
    public String toString() {
        return "ConsumerConfig [groupName=" + groupName + ", namesrvAddr=" + namesrvAddr + "]";
    }

}