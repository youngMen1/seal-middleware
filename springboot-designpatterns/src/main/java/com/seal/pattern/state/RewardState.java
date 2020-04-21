package com.seal.pattern.state;

import jdk.nashorn.internal.ir.RuntimeNode;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:46
 **/
public abstract class RewardState {
    abstract void doReward(RewardStateContext context, RuntimeNode.Request request);
}
