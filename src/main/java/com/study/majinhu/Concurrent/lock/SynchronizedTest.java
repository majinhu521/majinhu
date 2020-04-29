package com.study.majinhu.Concurrent.lock;

/**
 * @ClassName SynchronizedTest
 * @Description
 * 由于两个线程交叉执行，最后result的结果可能是0或者6或者3
 *
 * 共享变量不可见主要有下列原因：
 *
 * a.线程的交叉执行
 *
 * b.重排序
 *
 * c.共享变量未能及时更新
 * @Author majinhu
 * @Date 2019/8/20 10:07
 * @Version 1.0
 **/
public class SynchronizedTest {
        private boolean ready = false;
        private int result = 0;
        private int number = 1;

        public void write(){
            ready = true;
            number = 2;
        }

        public void read(){if(ready){
            result = number * 3;
        }

            System.out.println("result is " + result);
        }

        private class TestThread extends Thread{
            private boolean flag;
            public TestThread(boolean flag){
                this.flag = flag;
            }
            @Override
            public void run() {
                // TODO Auto-generated method stub
                if(flag){
                    write();
                }else{
                    read();
                }
            }
        }

        public static void main(String[] args){
            SynchronizedTest test = new SynchronizedTest();
            test.new TestThread(true).start();
            test.new TestThread(false).start();
        }

}
