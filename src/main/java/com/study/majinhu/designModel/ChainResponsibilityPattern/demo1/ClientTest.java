package com.study.majinhu.designModel.ChainResponsibilityPattern.demo1;

/**
 * @ClassName ClientTest
 * @Description https://baijiahao.baidu.com/s?id=1660719391207352664&wfr=spider&for=pc
 * @Author majinhu
 * @Date 2021/9/2 10:51
 * @Version 1.0
 **/
public class ClientTest {
    public static void main(String[] args){
        Handler zhangsan = new Director("张三");
        Handler lisi = new Manager("李四");
        Handler wangwu = new TopManager("王五");
        // 创建责任链
        zhangsan.setNextHandler(lisi);
        lisi.setNextHandler(wangwu);
        // 发起请假申请
        boolean result1 = zhangsan.process(new LeaveRequest("小旋锋", 1));
        System.out.println("最终结果：" + result1 + "\n");
        boolean result2 = zhangsan.process(new LeaveRequest("小旋锋", 4));
        System.out.println("最终结果：" + result2 + "\n");
        boolean result3 = zhangsan.process(new LeaveRequest("小旋锋", 8));
        System.out.println("最终结果：" + result3 + "\n");
    }
}
