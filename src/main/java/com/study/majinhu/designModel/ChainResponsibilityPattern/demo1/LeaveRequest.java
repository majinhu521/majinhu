package com.study.majinhu.designModel.ChainResponsibilityPattern.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName LeaveRequest
 * @Description 请假信息类，包含请假人姓名和请假天数
 * @Author majinhu
 * @Date 2021/9/2 10:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class LeaveRequest {
    private String name; // 请假人姓名
    private int numOfDays; // 请假天数
}
