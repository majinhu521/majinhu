package com.study.majinhu.Concurrent.distributelock.zk;


import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName ZKDistributeLock
 * @Description 分布式锁-改进版本。监听最小的节点。
 * 1尝试获取锁-2创建临时顺序节点-3获取子节点列表-4判断当前节点是否序号是否最小-5最小，占用锁执行，释放锁。
 * 6.不是最小，对比自己小的节点注册，watcher，等待锁。
 * @Author majinhu
 * @Date 2019/7/2 13:38
 * @Version 1.0
 **/
public class ZKDistributeLockImprove implements Lock {
    private String lockPath;
    private ZkClient zkClient;
    //当前目录
    private String currentPath;

    private String beforePath;

    public ZKDistributeLockImprove(String lockPath) {
        super();
        this.lockPath = lockPath;
        zkClient = new ZkClient("localhost:2181");
        zkClient.setZkSerializer(new MyZkSerializer());
    }
    //锁定
    @Override
    public void lock() {
        if(!tryLock()){
            waitForLock();
            lock();
        }
    }
    //尝试抢锁
    @Override
    public boolean tryLock() {
            if(this.currentPath == null){
                currentPath = this.zkClient.createEphemeralSequential(lockPath+"/","aaa");
            }
            //获取所有的子
            List<String> children = this.zkClient.getChildren(lockPath);
            //排序list
            Collections.sort(children);
            if(currentPath.equals(lockPath+"/"+children.get(0))){
                return true;
            }else{
                int curIndex = children.indexOf(currentPath.substring(lockPath.length()+1));
                beforePath = lockPath + "/"+children.get(curIndex-1);
            }
            return false;

    }

    //释放锁
    @Override
    public void unlock() {
        zkClient.delete(this.currentPath);
    }

    //等待锁
    private void waitForLock(){
        CountDownLatch cdl = new CountDownLatch(1);
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("收到节点被改变了");
                cdl.countDown();
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("收到节点被删除了");
                cdl.countDown();
            }
        };
        //进行监听
        zkClient.subscribeDataChanges(this.beforePath,listener);
        //阻塞自己
        if(this.zkClient.exists(this.beforePath)){
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //取消关注
        zkClient.unsubscribeDataChanges(this.beforePath,listener);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    @Override
    public Condition newCondition() {
        return null;
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
    }
}
