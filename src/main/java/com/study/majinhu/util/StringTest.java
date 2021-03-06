package com.study.majinhu.util;

/**
 * @ClassName StringTest
 * @Description
 * @Author majinhu
 * @Date 2020/10/13 10:05
 * @Version 1.0
 **/
public class StringTest {

    public static void main(String[] args){
        //String a ="123456";

        String format = String.format("flashSaleId=%s&from=%s", "123", "456");

        System.out.println(format);
        /**
         * 转换符	详细说明	示例
         * %s	字符串类型	“喜欢请收藏”
         * %c	字符类型	‘m’
         * %b	布尔类型	true
         * %d	整数类型（十进制）	88
         * %x	整数类型（十六进制）	FF
         * %o	整数类型（八进制）	77
         * %f	浮点类型	8.888
         * %a	十六进制浮点类型	FF.35AE
         * %e	指数类型	9.38e+5
         * %g	通用浮点类型（f和e类型中较短的）	不举例(基本用不到)
         * %h	散列码	不举例(基本用不到)
         * %%	百分比类型	％(%特殊字符%%才能显示%)
         * %n	换行符	不举例(基本用不到)
         * %tx	日期与时间类型（x代表不同的日期与时间转换符)	不举例(基本用不到)
         */
        String str=null;
        str=String.format("Hi,%s", "小超");
        System.out.println(str);
        str=String.format("Hi,%s %s %s", "小超","是个","大帅哥");
        System.out.println(str);
        System.out.printf("字母c的大写是：%c %n", 'C');
        System.out.printf("布尔结果是：%b %n", "小超".equals("帅哥"));
        System.out.printf("100的一半是：%d %n", 100/2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50*0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
    }
}
