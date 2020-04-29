package com.study.majinhu.Concurrent;

/**
 * @ClassName JoinTest
 * @Description 等待线程执行终止的 join 方法，需要等待某几件事情完成后才能继续往下执行。
 * 主线程里面启动了两个子线程，然后在分别调用了它们的 join() 方法，
 * 那么主线程首先会阻塞到 threadOne.join() 方法，
 * 等 threadOne 执行完毕后返回，threadOne 执行完毕后 threadOne.join() 就会返回。
 *
 * 然后主线程调用 threadTwo.join() 后再次被阻塞，等 threadTwo 执行完毕后主线程也就返回了。
 * @Author majinhu
 * @Date 2019/6/19 10:18
 * @Version 1.0
 **/
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threadOne over!");
        }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
             public void run() {
                try {
                     Thread.sleep(5000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                System.out.println("child threadTwo over!");
        }
        });
        //启动子线程
        threadOne.start();
        threadTwo.start();

        System.out.println("wait all child thread over!");
        //等待子线程执行完毕，等待返回，可注释掉看一下效果：注释掉后直接执行后一句，最后再执行线程。
        //threadOne.join();
        //threadTwo.join();

        System.out.println("all child thread over!");

    }
}
