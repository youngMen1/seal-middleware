package com.seal.rocketmq.springbootrocketmq.rocketmq.producer;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/20 17:50
 * @description 判断是否需要适合消息生产者配置
 **/
public class MQProducerCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//判断当前环境开关是否开启
		String isOnOff = context.getEnvironment().getProperty("rocketmq.producer.isOnOff");
		//当且仅当值为on时，返回true
		if(!StringUtils.isEmpty(isOnOff) && isOnOff.equalsIgnoreCase("on")){
			return true;
		}
		return false;
	}

}
