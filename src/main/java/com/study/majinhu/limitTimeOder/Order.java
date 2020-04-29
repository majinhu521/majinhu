package com.study.majinhu.limitTimeOder;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Order
 * @Description
 * @Author majinhu
 * @Date 2019/12/18 10:48
 * @Version 1.0
 **/
@Data
public class Order {
    private  String orderno;
    private  String status;
    private Date expireTime;
}
