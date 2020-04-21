package com.seal.pattern.state;

import jdk.nashorn.internal.ir.RuntimeNode;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:47
 * 待校验状态
 **/
public class OrderCheckState extends RewardState {
    @Override
    public void doReward(RewardStateContext context, RuntimeNode.Request request) {
        // 对进来的订单进行校验，判断是否用券，是否满足优惠条件等等
        //orderCheck(context, request);
    }
}

