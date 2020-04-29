package com.study.majinhu.Concurrent.distributelock.zk;

import com.study.majinhu.Concurrent.distributelock.zk.MyZkSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName ZKDistributeLock
 * @Description 分布式锁
 * @Author majinhu
 * @Date 2019/7/2 13:38
 * @Version 1.0
 **/
public class ZKDistributeLock implements Lock {
    private String lockPath;
    private ZkClient zkClient;

    public ZKDistributeLock(String lockPath) {
        super();
        this.lockPath = lockPath;
        zkClient = new ZkClient("localhost:2181");
        zkClient.setZkSerializer(new MyZkSerializer());
    }

    @Override
    public void lock() {
        if(!tryLock()){
            waitForLock();
            lock();
        }
    }

    @Override
    public boolean tryLock() {
        try{
            zkClient.createEphemeral(lockPath);
        }catch (ZkNodeExistsException e){
            return false;
        }
        return true;
    }

    @Override
    public void unlock() {
        zkClient.delete(lockPath);
    }


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
        zkClient.subscribeDataChanges(lockPath,listener);
        //阻塞自己
        if(this.zkClient.exists(lockPath)){
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //取消关注
        zkClient.unsubscribeDataChanges(lockPath,listener);
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
