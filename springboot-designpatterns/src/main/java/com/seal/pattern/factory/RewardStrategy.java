package com.seal.pattern.factory;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:11
 * 抽象策略
 **/
public abstract class RewardStrategy {

    public abstract void reward(long userId);

    // 更新用户信息以及结算
    public void insertRewardAndSettlement(long userId, int reward) {
    }
}
