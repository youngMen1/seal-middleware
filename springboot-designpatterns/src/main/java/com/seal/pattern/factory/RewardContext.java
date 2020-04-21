package com.seal.pattern.factory;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:13
 **/
public class RewardContext {
    private RewardStrategy strategy;

    public RewardContext(RewardStrategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy(long userId) {
//        int rewardMoney = strategy.reward(userId);
//        insertRewardAndSettlement(long userId, int reward) {
//            insertReward(userId, rewardMoney);
//            settlement(userId);
//        }
    }
}
