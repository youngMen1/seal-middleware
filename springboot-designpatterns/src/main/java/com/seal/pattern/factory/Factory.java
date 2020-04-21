package com.seal.pattern.factory;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/21 14:05
 * 抽象的工厂
 **/
public abstract class Factory<T> {
    abstract Product createProduct(Class c);
}