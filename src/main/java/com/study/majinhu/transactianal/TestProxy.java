package com.study.majinhu.transactianal;

import com.study.majinhu.orm.mybatis.entity.User;
import com.study.majinhu.orm.mybatis.mapper.User2Mapper;
import com.study.majinhu.orm.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TestProxy
 * @Description
 * * 另外一种办法是把带有注解的方法移到另外一个类中，
 *  * 其他类调用时候就会使用spring动态代理增强。
 *  * 比如我们把testAnnotation类移到TestProxy类中：
 *  事务失效的6种情况：
 *  https://baijiahao.baidu.com/s?id=1661565712893820457&wfr=spider&for=pc
 * @Author majinhu
 * @Date 2020/3/5 16:02
 * @Version 1.0
 **/
@Component
public class TestProxy {

//    @Autowired
//    private OperationLogMapper logMapper;
    @Autowired
    UserMapper UserMapper;
    @Autowired
    User2Mapper User2Mapper;
    /**
     * 不能被catch Exception，RuntimeException，事务会失效，还会继续插入执行，不会回滚。
     *
     * @throws InterruptedException
     */
    @Async
    @Transactional
    public void testAsyncAndTransactionalAnnotation() throws InterruptedException{
//        try {
            System.out.println("异步线程ID:" + Thread.currentThread().getId());
            OperationLog record = new OperationLog("2", "测试用");
//        logMapper.insert(record);
            User user2 = new User();
            user2.setName("2");
            user2.setPassword("2");
            User2Mapper.saveUser(user2);

            User user = new User();
            user.setName("1");
            user.setPassword("11111111111111111111111111111111");
            UserMapper.saveUser(user);


            System.out.println("插入记录");
//            throw new RuntimeException("故意抛出一个异常");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}