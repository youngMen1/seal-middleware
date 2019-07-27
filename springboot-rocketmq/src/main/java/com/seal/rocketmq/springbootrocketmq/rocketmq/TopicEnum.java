package com.seal.rocketmq.springbootrocketmq.rocketmq;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 定义消息队列主题
 * 注意：MQ消息队列主题，每个主题的子主题，请到tags下面定义
 **/
public enum TopicEnum {
    /**
     * 示例主题
     */
    DemoTopic("DemoTopic", "示例主题"),

    /**
     * 示例主题新
     */
    DemoNewTopic("DemoNewTopic", "示例主题新");

    private String code;
    private String msg;

    private TopicEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
