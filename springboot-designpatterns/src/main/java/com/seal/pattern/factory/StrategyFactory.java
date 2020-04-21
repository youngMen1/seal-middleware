package com.seal.pattern.factory;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:12
 * 抽象工厂
 **/
public abstract class StrategyFactory<T> {
    abstract RewardStrategy createStrategy(Class c);
}
