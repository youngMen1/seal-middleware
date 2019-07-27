package com.seal.rocketmq.springbootrocketmq.rocketmq.constants;

import java.io.Serializable;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 错误编码公共接口
 **/
public interface ErrorCode extends Serializable{
	/**
	 * 错误码
	 * @return
	 */
	String getCode();
	/**
	 * 错误信息
	 * @return
	 */
	String getMsg();
}
