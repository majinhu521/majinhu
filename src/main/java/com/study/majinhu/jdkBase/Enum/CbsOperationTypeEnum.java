package com.study.majinhu.jdkBase.Enum;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * 预约，发货，签收操作类型枚举
 * GY预约1，CK发货2，QS签收3 PD派单4 PR派送5
 * @date: Created in 2020/12/18 11:09
 * @version:
 * @modified By:
 */
public enum CbsOperationTypeEnum {
    //预约1，发货2，签收3 PD派单4 PR派送5
    GY(1,"GY","改约"),
    CK(2,"CK","发货"),
    QS(3,"QS","签收"),
    PD(4,"PD","派单"),
    PR(5,"PR","派送");

    private Integer type;
    private String name;
    private String desc;
    CbsOperationTypeEnum(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static CbsOperationTypeEnum getByType(Integer type) {
        if (null == type) {
            return null;
        }
        CbsOperationTypeEnum[] values = CbsOperationTypeEnum.values();
        for (CbsOperationTypeEnum value : values) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }
    public static CbsOperationTypeEnum getByName(String name) {
        if (null == name) {
            return null;
        }
        CbsOperationTypeEnum[] values = CbsOperationTypeEnum.values();
        for (CbsOperationTypeEnum value : values) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }

    public static void main(String[] args){
        CbsOperationTypeEnum operationTypeEnum = CbsOperationTypeEnum.getByName("GY");
        if (null == operationTypeEnum) {
//            result.setMessage("操作类型错误");
//            return result;
            System.out.println("操作类型错误");
        }
        switch (operationTypeEnum) {
            case CK:
                System.out.println("CK");
                break;
            case GY:
                System.out.println("GY");
                System.out.println(operationTypeEnum.getType().toString());
                System.out.println(operationTypeEnum.getDesc().toString());
                break;
            case PD:
                System.out.println("PD");
                break;
            case PR:
                System.out.println("PR");
                break;
            case QS:
                System.out.println("QS");
                break;
            default:
        }
    }

}
