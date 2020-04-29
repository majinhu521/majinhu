package com.study.majinhu.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName PropertiesUtils
 * @Description 动脑学院学习配置中心。
 * @Author majinhu
 * @Date 2020/2/19 8:51
 * @Version 1.0
 **/
@Component
public class PropertiesUtils {
    Properties remoteProperties = new Properties();

    @Autowired
    Environment environment;

//    @Value("${pay.alipay.url}")
//    private String alipay;

    /**
     * @Author majinhu
     * @Description  获取配置文件
     * @Date 8:53 2020/2/19
     * @Param []
     * @return java.lang.String
     **/
    public String getProperty(String key){
        String value=remoteProperties.getProperty(key);
        if(value == null){
            //本地获取，从spring获取配置文件。
            value = environment.getProperty(key);
        }
        return  remoteProperties.getProperty(key);
    }
    /**
     * @Author majinhu
     * @Description 初始化配置文件。
     * 1.从远处读取配置文件保存到本地。
     * https://www.cnblogs.com/qingyunzong/p/8666288.html
     * 2.远程更新配置文件，本地同时更新。
     * @Date 8:53 2020/2/19
     * @Param []
     * @return void
     **/
    @PostConstruct
    public void init(){
        CuratorFramework zkClient = CuratorFrameworkFactory.newClient("127.0.0.1.2181", new RetryOneTime(1000));
        zkClient.start();

        try {
            //1.从远程配置中心读取文件。
            List<String> configNames = zkClient.getChildren().forPath("/pay-server-config");
            for (String configName:configNames) {
                //2.本地进行保存到配置信息。
                byte[] configValue = zkClient.getData().forPath("/pay-server-config" + configName);
                remoteProperties.put(configName,new String(configValue));
                //3.同步更新。
                TreeCache treeCache = new TreeCache(zkClient, "/pay-server-config");
                treeCache.start();
                treeCache.getListenable().addListener(new TreeCacheListener() {
                    @Override
                    public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                        switch (treeCacheEvent.getType()){//获取事件的类型
                            case NODE_UPDATED:
                                System.out.println("数据发生了变动"+treeCacheEvent.getData());//数据的值
                                //变动的具体位置：path： /pay-server-config/pay.alipay.url
                                String key = treeCacheEvent.getData().getPath().replace("/pay-server-config","");//pay.alipay.url
                                String value = new String(treeCacheEvent.getData().getData());//http:*****/v3/gateway.do
                                remoteProperties.setProperty(key,value);
                                break;
                                default:
                                    break;
                        }
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
