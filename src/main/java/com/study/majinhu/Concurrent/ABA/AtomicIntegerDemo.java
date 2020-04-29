package com.study.majinhu.Concurrent.ABA;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerDemo
 * @Description
 * @Author majinhu
 * @Date 2019/6/27 10:30
 * @Version 1.0
 **/
public class AtomicIntegerDemo {
    static AtomicInteger x = new AtomicInteger(0);

    public static void main(String[] args) {

        System.out.println("get()返回当前AtomicInteger变量的值：" + x.get());

        System.out.println("getAndIncrement()返回x++的值：" + x.getAndIncrement() + " ，X的当前值为" + x.get());
        System.out.println("incrementAndGet()返回++x的值：" + x.incrementAndGet() + " ，X的当前值为" + x.get());

        System.out.println("getAndDecrement()返回x--的值：" + x.getAndDecrement() + " ，X的当前值为" + x.get());
        System.out.println("decrementAndGet()返回--x的值：" + x.decrementAndGet() + " ，X的当前值为" + x.get());

        System.out.println("getAndAdd()返回x+=10前的值：" + x.getAndAdd(10) + " ，X的当前值为" + x.get());
        System.out.println("addAndGet()返回x+=10后的值：" + x.addAndGet(10) + " ，X的当前值为" + x.get());

        System.out.println("getAndUpdate()函数的结果更新当前值，返回更新前的值：" + x.getAndUpdate(t -> 100) + " ，X的当前值为" + x.get());
        System.out.println("updateAndGet()函数的结果更新当前值，返回更新后的值：" + x.updateAndGet(t -> 666) + " ，X的当前值为" + x.get());

        System.out.println("getAndAccumulate()使用IntBinaryOperator对当前值和第一个参数进行计算，并更新当前值，返回计算前的值：" + x.getAndAccumulate(100, (s1, s2) -> s1 + s2) + " ，X的当前值为" + x.get());
        System.out.println("accumulateAndGet()使用IntBinaryOperator对当前值和第一个参数进行计算，并更新当前值，返回计算后的值：" + x.accumulateAndGet(100, (s1, s2) -> s1 + s2) + " ，X的当前值为" + x.get());

        System.out.println("getAndSet()设定当前的值，返回旧值：" + x.getAndSet(10) + " ，X的当前值为" + x.get());
        x.set(30);
        System.out.println("set()设定X的当前值为" + x.get());
        x.lazySet(20);
        System.out.println("lazySet()设定X的当前值为" + x.get());

        //CAS操作，如果x当前值为20则设置x值为10并返回true，否则返回false
        System.out.println("compareAndSet()判断x当前值是否为20：" + x.compareAndSet(20, 10) + " ，X的当前值为" + x.get());
        //CAS操作，如果x当前值为10则设置x值为20并返回true，否则返回false
        System.out.println("weakCompareAndSet()判断x当前值是否为10：" + x.weakCompareAndSet(10, 20) + " ，X的当前值为" + x.get());

        Object shortObj = x.shortValue();
        System.out.println("shortValue()将当前值转为short类型：" + (shortObj instanceof Short)+x.shortValue());
        Object intObj = x.intValue();
        System.out.println("intValue()将当前值转为int类型：" + (intObj instanceof Integer));
        Object longObj = x.longValue();
        System.out.println("longValue()将当前值转为long类型：" + (longObj instanceof Long)+x.longValue());
        Object doubleObj = x.doubleValue();
        System.out.println("doubleValue()将当前值转为double类型：" + (doubleObj instanceof Double));
        Object floatObj = x.floatValue();
        System.out.println("floatValue()将当前值转为float类型：" + (floatObj instanceof Float));
    }

}
