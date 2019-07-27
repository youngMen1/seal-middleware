package com.seal.rocketmq.springbootrocketmq.rocketmq.constants;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 消息发送状态：
 * -201：定时任务发送失败（人工处理），
 * -200：生产者发送失败（定时任务处理），
 * 100：待发送，200：生产者发送成功，
 * 201：定时任务发送成功
 **/
public enum ProductSendStateEnum {
    /**
     * 消息发送状态：
     * -201：定时任务发送失败（人工处理）
     */
    JOB_SEND_FAIL(-201),

    /**
     * -200：生产者发送失败（定时任务处理）
     */
    PRODUCT_SEND_FAIL(-200),

    /**
     * 100：待发送，200：生产者发送成功
     */
    WAIT_SEND(100),

    /**
     * 成功
     */
    PRODUCT_SEND_SUCCESS(200),

    /**
     * 01：定时任务发送成功
     */
    JOB_SEND_SUCCESS(201),
    ;
    private int code;

    private ProductSendStateEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
