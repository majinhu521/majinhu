package com.study.majinhu.designModel.CommandPattern;

/**
 * @ClassName BuyStock
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:10
 * @Version 1.0
 **/
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}