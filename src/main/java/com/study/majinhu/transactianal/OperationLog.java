package com.study.majinhu.transactianal;

/**
 * @ClassName OperationLog
 * @Description
 * @Author majinhu
 * @Date 2020/3/5 15:57
 * @Version 1.0
 **/
public class OperationLog {
    private  String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperationLog(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
