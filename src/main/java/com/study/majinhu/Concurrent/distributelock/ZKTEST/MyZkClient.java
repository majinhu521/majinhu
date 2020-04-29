package com.study.majinhu.Concurrent.distributelock.ZKTEST;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

/**
 * @ClassName MyZkClient
 * @Description
 * @Author majinhu
 * @Date 2019/7/2 15:06
 * @Version 1.0
 **/
public class MyZkClient extends ZkClient {

        public MyZkClient(String zkServers, int sessionTimeout, int connectionTimeout, ZkSerializer zkSerializer) {
            super(zkServers, sessionTimeout, connectionTimeout, zkSerializer);
        }

        @Override
        public void watchForData(final String path) {
            retryUntilConnected(() -> {
                System.out.println("进入重写的watchForData方法了！要被监听的节点path是:" + path);
                Stat stat = new Stat();
                _connection.readData(path, stat, true);
                // 监听节点时，若节点不存在 则抛出异常
                if (!exists(path)) {
                    throw new KeeperException.NoNodeException();
                }
                return null;
            });
        }
}


