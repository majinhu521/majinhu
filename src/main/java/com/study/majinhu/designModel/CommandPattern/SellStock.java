package com.study.majinhu.designModel.CommandPattern;

/**
 * @ClassName SellStock
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:10
 * @Version 1.0
 **/
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}