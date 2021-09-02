package com.study.majinhu.lambda;

/**
 * @ClassName Lambdatest
 * @DescriptionB https://blog.csdn.net/GoGleTech/article/details/79454151
 * https://blog.csdn.net/bitcarmanlee/article/details/70195403?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight
 * @Author majinhu
 * @Date 2020/9/7 11:30
 * @Version 1.0
 **/
public class Lambdatest {
    private static String isStoreSate(Byte storeState) {
        if (storeState == null) {
            return "";
        }
        if (storeState == (byte) 3) {
            return "正常营业";
        } else if (storeState == (byte) 4) {
            return "暂停营业";
        } else if (storeState == (byte) 6) {
            return "取消合作";
        } else {
            return "";
        }
    }

    private static String isCustomerNature(Byte customerNature) {
        if (customerNature == null) {
            return "";
        }
        if (customerNature == (byte) 0) {
            return "伞下店";
        } else if (customerNature == (byte) 1) {
            return "直营";
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(isStoreSate(null));
        System.out.println(isCustomerNature(null));
    }

}
