package com.study.majinhu.jijin;

import com.alipay.api.domain.AlipayBossBaseProcessSignVerifyModel;

import java.math.BigDecimal;

/**
 * @ClassName Jijin
 * @Description 基金计算
 * @Author majinhu
 *
基金收益组成

基金的收益一般由红利收益和买卖价差收益两部分组成，接下来就以1000元为例来介绍基金收益的具体计算过程。

以1000元为例计算收益

假设某天买入1000元基金，当天基金的结算净值为1.165，申购费率为0.5%，[1000/(1+0.5%)]/1.165]=854.098，所以持有的基金份额为854.098份。

期间基金发放过一次红利，每一分额基金派发红利0.14元，则获得红利0.14*854.098=119.573元。

赎回当天的基金净值为1.2120，赎回费率为0.4%，854.098*1.2120*（1-0.4%）=1031.026元，所以买卖差价收益为31元，加上之前的红利一共的收益为150元。

结语：其实只要知道了计算方法，自己算出基金收益也是一件很简单的事情，大家也可以自己试一试。


https://zhidao.baidu.com/question/41913309.html

认\申购基金收益的计算方法

收益份额计算分为：外扣法与内扣法：

1、内扣法：

份额 = 投资金额×（1－认\申购费率）÷认\申购当日净值+利息

收益 =赎回当日单位净值×份额×（1－赎回费率）+红利－投资金额

2、外扣法:

份额 = 投资金额×（1+认\申购费率）÷认\申购当日净值+利息

收益 = 赎回当日单位净值×份额×（1－赎回费率）+红利－投资金额

现在大部分基金公司采用的都是外扣法，因为同样的申购金额，外扣法购买的份额会多一点，对基金民比较有利。

用此方法可以计算每日自己的盈利情况。购买基金后，如果觉得每日计算比较麻烦，可以采用财道网的基金账本进行管理，可以自动计算出每天的收益及收益率.
 * @Date 2019/12/30 14:27
 * @Version 1.0
 **/
public class Jijin {

    //投资金额
    BigDecimal BUY_TZJE =new  BigDecimal("1000");
    //申购费率
    BigDecimal BUY_SGFL =new  BigDecimal("0.005");
    //申购当日净值
    BigDecimal BUY_JZ =new  BigDecimal("1.165");
    BigDecimal FENE;
    BigDecimal SHOUYI;
    //赎回当日净值
    BigDecimal SELL_JZ =new  BigDecimal("1.2120");
    //赎回当日费率
    BigDecimal SELL_SHFL=new  BigDecimal("0.004");
    //红利
    BigDecimal HL =new  BigDecimal("119.573");

    BigDecimal ONE =new  BigDecimal("1");
    public  BigDecimal shouyi(){
        FENE = BUY_TZJE.divide(ONE.add(BUY_SGFL),3,BigDecimal.ROUND_HALF_UP).divide(BUY_JZ,3,BigDecimal.ROUND_HALF_UP);

        System.out.println("份额为："+FENE);
        //加分红，分红=每份分红*份额。
//        SHOUYI =SELL_JZ.multiply(FENE).multiply(ONE.subtract(SELL_SHFL)).add(HL).subtract(BUY_TZJE);
        SHOUYI=SELL_JZ.multiply(FENE).multiply(ONE.subtract(SELL_SHFL)).subtract(BUY_TZJE);
        return SHOUYI;
    }


    public static void main(String[] args) {
        Jijin jj = new Jijin();
        System.out.println("收益为："+jj.shouyi());
    }

}
