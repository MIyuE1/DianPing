package com.hmdp.utils;

public interface ILock {
    boolean tryLock(long timeoutSec);

    void unlock();
}
