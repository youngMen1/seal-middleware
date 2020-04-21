package com.seal.pattern.state;

import jdk.nashorn.internal.ir.RuntimeNode;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:47
 * 待补偿状态
 **/
public class CompensateRewardState extends RewardState {
    @Override
    public void doReward(RewardStateContext context, RuntimeNode.Request request) {
        // 返奖失败，需要对用户进行返奖补偿
        // compensateReward(context, request);
    }
    // 预返奖状态，待返奖状态，成功状态，失败状态(此处逻辑省略)
    // ..
}

