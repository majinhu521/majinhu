package com.study.majinhu.Concurrent.distributelock;

import com.study.majinhu.Concurrent.distributelock.zk.MyZkSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * @ClassName ZKWatchDemo
 * @Description
 * @Author majinhu
 * @Date 2019/7/2 11:54
 * @Version 1.0
 **/
public class ZKWatchDemo {
    public static void main(String[] args) {
        ZkClient client = new ZkClient("localhost:2181");
        client.setZkSerializer(new MyZkSerializer());
        client.subscribeDataChanges("/lock/aaa", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("接收到节点被改变");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("接收到节点被删除");
            }
        });
        try {
            Thread.sleep(1000*60*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
