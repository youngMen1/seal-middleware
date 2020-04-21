package com.seal.pattern.factory;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:09
 * 具体的策略实现(可以定义多个具体的策略实现)
 **/
public class StrategyA implements Strategy{
    @Override
    public void strategyImplementation() {
        System.out.println("正在执行策略A");
    }
}
