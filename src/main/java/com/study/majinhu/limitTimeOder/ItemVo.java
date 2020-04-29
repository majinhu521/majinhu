package com.study.majinhu.limitTimeOder;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DelayQueItemVo
 * @Description 构造泛型包装实现Delayed接口，放入DelayQue，限时订单。
 * @Author majinhu
 * @Date 2019/12/18 10:23
 * @Version 1.0
 **/
public class ItemVo<T> implements Delayed {
    //到期时间 20:05:25,234
    private long activeTime;
    private T data;
    //传入过期时长为秒，转为毫秒
    public ItemVo(long expirationTime, T data) {
        super();
        this.activeTime = expirationTime*1000+System.currentTimeMillis();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //返回元素到激活时间的剩余时长
    @Override
    public long getDelay(TimeUnit unit) {
        //还剩多少毫秒，过期时间-当前时间
        long d = unit.convert(this.activeTime-System.currentTimeMillis(),unit);
        return d;
    }
    //按照剩余时间比较排序，剩余最少得排到前面。
    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS);
        if(d == 0){
            return 0;
        }else{
            if (d<0){
                return -1;
            }else {
                return 1;
            }
        }

    }
}
