package com.study.majinhu.designModel.Builder;

/**
 * @ClassName Item
 * @Description 类目：名称，包装，价格。
 * @Author majinhu
 * @Date 2019/8/15 9:55
 * @Version 1.0
 **/
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
