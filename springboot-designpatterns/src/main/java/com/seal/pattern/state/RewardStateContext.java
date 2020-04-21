package com.seal.pattern.state;

import jdk.nashorn.internal.ir.RuntimeNode;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:45
 * 返奖状态执行的上下文
 **/
public class RewardStateContext {
    private RewardState rewardState;

    public void setRewardState(RewardState currentState) {this.rewardState = currentState;}
    public RewardState getRewardState() {return rewardState;}
    public void echo(RewardStateContext context, RuntimeNode.Request request) {
        rewardState.doReward(context, request);
    }
}
