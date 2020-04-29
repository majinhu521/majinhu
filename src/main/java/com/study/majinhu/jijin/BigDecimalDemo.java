package com.study.majinhu.jijin;

import java.math.BigDecimal;

/**
 * @ClassName BigDecimalDemo
 * @Description
 * @Author majinhu
 * @Date 2020/2/26 13:59
 * @Version 1.0
 **/
public class BigDecimalDemo {

    public static void main(String[] args) {


            // create 4 BigDecimal objects
            BigDecimal bg1, bg2, bg3, bg4;

            bg1 = new BigDecimal("235.000");
            bg2 = new BigDecimal("0.10");

            // assign the result of stripTrailingZeros method to bg3, bg4
            bg3 = bg1.stripTrailingZeros();
            bg4 = bg2.stripTrailingZeros();

            String str1 = bg1 + " after removing trailing zeros " +bg3.toPlainString();
            String str2 = bg2 + " after removing trailing zeros " +bg4.toPlainString();

            // print bg3, bg4 values
            System.out.println( str1 );
            System.out.println( str2 );
        }//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java/math/bigdecimal_striptrailingzeros.html


}
