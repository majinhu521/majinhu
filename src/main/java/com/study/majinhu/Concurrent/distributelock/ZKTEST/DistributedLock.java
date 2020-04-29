package com.study.majinhu.Concurrent.distributelock.ZKTEST;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DistributedLock
 * @Description
 *  https://github.com/JustryDeng/CommonRepository
 * @Author majinhu
 * @Date 2019/7/2 14:49
 * @Version 1.0
 **/
public interface DistributedLock {

        /*
         * 获取锁，如果没有得到就等待
         */
        void acquire() throws Exception;

        /*
         * 获取锁，直到超时
         */
        boolean acquire(long time, TimeUnit unit) throws Exception;

        /*
         * 释放锁
         */
        void release() throws Exception;

    }


