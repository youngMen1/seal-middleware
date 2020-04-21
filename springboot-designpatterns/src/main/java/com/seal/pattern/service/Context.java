package com.seal.pattern.service;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:09
 * 封装策略，屏蔽高层模块对策略、算法的直接访问，屏蔽可能存在的策略变化
 **/
public class Context {
    private Strategy strategy = null;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy() {
        strategy.strategyImplementation();
    }
}