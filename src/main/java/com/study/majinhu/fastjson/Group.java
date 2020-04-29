package com.study.majinhu.fastjson;

/**
 * @ClassName Group
 * @Description
 * @Author majinhu
 * @Date 2020/2/26 15:59
 * @Version 1.0
 **/
public class Group {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
