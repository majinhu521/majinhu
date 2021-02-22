package com.study.majinhu.Concurrent.volatileTest;

import com.study.majinhu.Concurrent.lock.VolatileTest;

/**
 * @ClassName VolatileTestt
 * @Description
 * https://www.cnblogs.com/xd502djj/p/9873067.html
 * @Author majinhu
 * @Date 2021/2/20 17:27
 * @Version 1.0
 **/

public class VolatileTestt extends Thread {

    //boolean flag = false;
    //主线程将vt.flag的值同样 从主内存中拷贝到自己的线程工作内存 然后修改flag=true. 然后再将新值回到主内存。
    //这就解释了为什么在主线程（main）中设置了vt.flag = true; 而vt线程在进行判断flag的时候拿到的仍然是false。
    // 那就是因为vt线程每次判断flag标记的时候是从它自己的“工作内存中”取值，而并非从主内存中取值！
    volatile boolean flag = false;
    int i = 0;

    public void run() {
        System.out.println("=======before");
        while (!flag) {
            i++;
        }
        System.out.println("=======done");
    }

    public static void main(String[] args) throws Exception {
        VolatileTestt vt = new VolatileTestt();
        vt.start();
        Thread.sleep(2000);
        vt.flag = true;
        System.out.println("stope" + vt.i);
    }
}
