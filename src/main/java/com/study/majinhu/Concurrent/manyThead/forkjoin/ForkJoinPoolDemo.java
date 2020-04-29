package com.study.majinhu.Concurrent.manyThead.forkjoin;

/**
 * @ClassName ForkJoinPoolDemo
 * @Description 批量发送消息
 * 因此RecursiveAction与RecursiveTask区别在与RecursiveTask是有返回结果而RecursiveAction是没有返回结果
 * https://www.cnblogs.com/cjsblog/p/9078341.html
 * @Author majinhu
 * @Date 2020/3/13 14:44
 * @Version 1.0
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolDemo {

    class SendMsgTask extends RecursiveAction {

        private final int THRESHOLD = 10;

        private int start;
        private int end;
        private List<String> list;

        public SendMsgTask(int start, int end, List<String> list) {
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected void compute() {

            if ((end - start) <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + list.get(i));
                }
            }else {
                int middle = (start + end) / 2;
                invokeAll(new SendMsgTask(start, middle, list), new SendMsgTask(middle, end, list));
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            list.add(String.valueOf(i+1));
        }

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ForkJoinPoolDemo().new SendMsgTask(0, list.size(), list));
        pool.awaitTermination(100, TimeUnit.SECONDS);
        pool.shutdown();
    }

}