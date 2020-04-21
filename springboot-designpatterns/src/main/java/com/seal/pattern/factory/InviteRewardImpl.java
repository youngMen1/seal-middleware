package com.seal.pattern.factory;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:15
 * 接下来我们将工厂模式和策略模式结合在一起，就完成了整个返奖的过程
 **/
public class InviteRewardImpl {
    //返奖主流程
//    public void sendReward(long userId) {
//        FactorRewardStrategyFactory strategyFactory = new FactorRewardStrategyFactory();  //创建工厂
//       // Invitee invitee = getInviteeByUserId(userId);  //根据用户id查询用户信息
//        if (invitee.userType == UserTypeEnum.NEW_USER) {  //新用户返奖策略
//            NewUserBasicReward newUserBasicReward = (NewUserBasicReward) strategyFactory.createStrategy(NewUserBasicReward.class);
//            RewardContext rewardContext = new RewardContext(newUserBasicReward);
//            rewardContext.doStrategy(userId); //执行返奖策略
//        }if(invitee.userType == UserTypeEnum.OLD_USER){}  //老用户返奖策略，...
//    }
}
