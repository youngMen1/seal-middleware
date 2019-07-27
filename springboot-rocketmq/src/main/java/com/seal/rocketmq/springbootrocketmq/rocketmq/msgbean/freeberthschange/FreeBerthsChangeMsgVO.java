package com.seal.rocketmq.springbootrocketmq.rocketmq.msgbean.freeberthschange;

import com.seal.rocketmq.springbootrocketmq.rocketmq.msgbean.base.BaseMQMessageVO;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 空闲车位通知消息对象
 **/
public class FreeBerthsChangeMsgVO extends BaseMQMessageVO {
    /**
     * 停车场编号
     */
    private String plNo;
    /**
     * 停车场名称
     */
    private String plName;
    /**
     * 当前空闲车位数
     */
    private int freeBerths;

    public FreeBerthsChangeMsgVO(String plNo, String plName, int freeBerths) {
        super();
        this.plNo = plNo;
        this.plName = plName;
        this.freeBerths = freeBerths;
    }

    public String getPlNo() {
        return plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public int getFreeBerths() {
        return freeBerths;
    }

    public void setFreeBerths(int freeBerths) {
        this.freeBerths = freeBerths;
    }

    @Override
    public String toString() {
        return "FreeBerthsChangeMsgVO [plNo=" + plNo + ", plName=" + plName + ", freeBerths=" + freeBerths + "]";
    }

}
