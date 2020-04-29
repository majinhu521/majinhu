package com.study.majinhu.Concurrent.manyThead;

/**
 * @ClassName OperationHistoryData
 * @Description
 * @Author majinhu
 * @Date 2020/2/18 16:36
 * @Version 1.0
 **/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 操作日志线程类
 * @author liguobao
 *https://www.jianshu.com/p/e1adaae523ec
 */
//@Service("OperationHistoryService")
public class OperationHistoryService {

    private static final Logger log = LoggerFactory.getLogger(OperationHistoryService.class);

    private CountDownLatch threadsSignal;
    //每个线程处理的数据量
    private static final int count=1000;
    @Autowired
    OperationHistoryMapper operationHistoryMapper;

    //定义线程池数量为8,每个线程处理1000条数据
    private static ExecutorService execPool = Executors.newFixedThreadPool(8);

    /**
     * 多线程批量执行插入，百万数据需要大约不到20秒   64位4核处理
     * @param request
     * @return
     */
    public String batchAddData(OperationHistortRequest request) {
        //存放每个线程的执行数据
        //List<OperationHistoryModel> newlist = null;
        OperationHistortResponse response=new OperationHistortResponse();
        //需要插入数据库的数据
        List<OperationHistoryModel> limodel=request.getOperationHistoryLi();
        try {

            if(limodel.size()<=count) {
                threadsSignal=new CountDownLatch(1);
                execPool.submit(new InsertData(limodel));
            }else {
                List<List<OperationHistoryModel>> list=createList(limodel, count);
                threadsSignal=new CountDownLatch(list.size());
                for(List<OperationHistoryModel> liop:list) {
                    execPool.submit(new InsertData(liop));
                }
            }
            threadsSignal.await();
            response.setCode(0);
            response.setMsg("success");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            response.setCode(0);
            response.setMsg(e.getMessage());
            log.error(e.toString() + " 错误所在行数：" + e.getStackTrace()[0].getLineNumber());
        }

        return JSON.toJSONString(response, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 数据拆分
     * @param targe
     * @param size
     * @return
     */
    public static List<List<OperationHistoryModel>>  createList(List<OperationHistoryModel> targe,int size) {
        List<List<OperationHistoryModel>> listArr = new ArrayList<List<OperationHistoryModel>>();
        //获取被拆分的数组个数
        int arrSize = targe.size()%size==0?targe.size()/size:targe.size()/size+1;
        for(int i=0;i<arrSize;i++) {
            List<OperationHistoryModel>  sub = new ArrayList<OperationHistoryModel>();
            //把指定索引数据放入到list中
            for(int j=i*size;j<=size*(i+1)-1;j++) {
                if(j<=targe.size()-1) {
                    sub.add(targe.get(j));
                }
            }
            listArr.add(sub);
        }
        return listArr;
    }


    /**
     * 内部类,开启线程批量保存数据
     * @author liguobao
     *
     */
    class  InsertData  extends Thread{

        List<OperationHistoryEntity> lientity=new ArrayList<OperationHistoryEntity>();

        public  InsertData(List<OperationHistoryModel> limodel){
            limodel.forEach((model)->{
                OperationHistoryEntity oper=model.getOperationHistoryEntity(model);
                lientity.add(oper);
            });
        }

        public void run() {
            operationHistoryMapper.addOperationHistory(lientity);
            threadsSignal.countDown();
        }
    }
}