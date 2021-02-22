package com.study.majinhu.jdkBase.Enum;

/**
 * db_big_data.member_uc_log.log_type枚举类
 * @author gp
 * @date 2021-01-13
 */
public enum MemberUcLogType {

    UPDATE_MEMBER_ID(1,"修改用户id"),
    UPDATE_UC_ID(2,"修改用户中心id"),
    DEL_MEMBER_UC(3,"删除映射关系"),
    ADD_MEMBER_UC(4,"新增映射关系");

    MemberUcLogType(Integer type, String describe) {
        this.type = type;
        this.describe = describe;
    };

    public static String getDesc(Integer value) {
        MemberUcLogType[] loginTypeEnums = values();
        for (MemberUcLogType logTypeEnum : loginTypeEnums) {
            if (logTypeEnum.getType().equals(value)) {
                return logTypeEnum.describe;
            }
        }
        return null;
    }

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 描述
     */
    private final String describe;

    public Integer getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }
}
