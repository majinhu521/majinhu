package com.study.majinhu.designModel.CommandPattern;

/**
 * @ClassName CommandPatternDemo
 * @Description 命令模式
 * https://www.runoob.com/design-pattern/command-pattern.html
 * @Author majinhu
 * @Date 2020/5/18 10:11
 * @Version 1.0
 **/
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
