package com.seal.rocketmq.springbootrocketmq.rocketmq.consumer.processor;

import java.io.Serializable;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 消费结果
 **/
public class MQConsumeResult implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 是否处理成功
     */
    private boolean isSuccess;
    /**
     * 如果处理失败，是否允许消息队列继续调用，直到处理成功，默认true
     */
    private boolean isReconsumeLater = true;
    /**
     * 是否需要记录消费日志，默认不记录
     */
    private boolean isSaveConsumeLog = false;
    /**
     * 错误Code
     */
    private String errCode;
    /**
     * 错误消息
     */
    private String errMsg;
    /**
     * 错误堆栈
     */
    private Throwable e;

    /**
     * 设置错误信息
     *
     * @param errCode
     * @param errMsg
     * @param e       2018年3月1日 zhaowg
     */
    public void setErrInfo(String errCode, String errMsg, Throwable e) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.e = e;
        this.isSuccess = false;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }

    public boolean isReconsumeLater() {
        return isReconsumeLater;
    }

    public void setReconsumeLater(boolean isReconsumeLater) {
        this.isReconsumeLater = isReconsumeLater;
    }

    public boolean isSaveConsumeLog() {
        return isSaveConsumeLog;
    }

    public void setSaveConsumeLog(boolean isSaveConsumeLog) {
        this.isSaveConsumeLog = isSaveConsumeLog;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


}
