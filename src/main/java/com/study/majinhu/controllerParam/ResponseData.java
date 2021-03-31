package com.study.majinhu.controllerParam;

/**
 * @ClassName ResponseData
 * @Description
 * @Author majinhu
 * @Date 2021/3/18 15:24
 * @Version 1.0
 **/
public class ResponseData {

    private Boolean status = true;
    private int code = 200;
    private String message;
    private Object data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
