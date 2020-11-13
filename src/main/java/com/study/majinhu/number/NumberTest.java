package com.study.majinhu.number;

/**
 * @ClassName NumberTest
 * @Description
 *
 * @Author majinhu
 * @Date 2020/6/16 13:40
 * @Version 1.0
 **/
public class NumberTest {
    //商品列表（首页、搜索列表、分类列表、店铺商品列表、优惠券适用商品列表）增加评价信息、好评率，无评价显示“暂无评价”。
    //
    //评价数量显示规则：
    //（1）只有个位数，则显示个位数
    //
    //（2）两位数，十位数显示具体数字，个位数“0”代替。如：90+
    //
    //（3）三位数，百位显示具体数字，个位、十位“0”代替，如：800+
    //
    //（4）四位数，千位、百位显示具体数字，个位、十位“0”代替，如：2800+
    //
    //（5）五位数及以上，数字+“万”，如，4.2万

    private static String getpinjiaonum(int num){
        if(0<=num && num <=9){
            return String.valueOf(num);
        }else if(10<= num && num <= 99){
            return String.valueOf(num).substring(0,1)+"0+";
        }else if(100<= num && num <= 999){
            return String.valueOf(num).substring(0,1)+"00+";
        }else if(1000<= num && num <= 9999){
            return String.valueOf(num).substring(0,2)+"00+";
        }else if(10000<= num && num <= 99999){
            String numSplit=Integer.valueOf(num/1000).toString();
            return numSplit.substring(0,1)+"."+numSplit.substring(1)+"万+";
        }else if(100000<= num ){
           return Integer.valueOf(num/10000).toString()+"万+";
        }
        return "";
    }
    public static void main(String[] args){
        System.out.println("数字是"+NumberTest.getpinjiaonum(99999));
    }

}
