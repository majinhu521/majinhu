package com.study.majinhu.jvm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @ClassName StackTest
 * @Description 栈测试，先进后出
 * @Author majinhu
 * @Date 2019/12/26 14:44
 * @Version 1.0
 **/
public class StackTest {
    private static void queue()//  队列
    {
        Queue<String> queue = new LinkedList<>();
        queue.offer("Hello");
        queue.offer("world");
        queue.offer("I m find");
        System.out.println("--------------------------");
        System.out.println(queue.size());
        String aa ;
        while((aa = queue.poll()) !=null)
        {
            System.out.println("queue---"+aa);
        }
        System.out.println(queue.size());
        System.out.println("--------------------------");
    }


    private static void stack() // 堆栈
    {
        Vector<String> stack = new Vector<String>();
        stack.add("aaa");
        stack.add("bbb");
        stack.add("ccc");
        stack.add("ddd");
        stack.add("fff");
        System.out.println("---------------------------");
        System.out.println("size"+stack.size());

        String aa ;
        try {
            while((aa = stack.lastElement()) !=null)
            {
                System.out.println("a---"+aa);
                stack.removeElement(aa);
//break;
            }
        } catch (Exception e) {
// TODO: handle exception
        }
        System.out.println("size"+stack.size());

    }
//队列和堆栈区别：--- 先进先出和先进后出
    public static void main(String[] args) {
       // StackTest.queue();
     StackTest.stack();
    }

}
