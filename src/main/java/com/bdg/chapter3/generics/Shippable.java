package com.bdg.chapter3.generics;


public interface Shippable<T> {
    void ship(T t);
}