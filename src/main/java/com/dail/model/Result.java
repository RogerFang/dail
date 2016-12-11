package com.dail.model;

/**
 * Created by Roger on 2016/12/11.
 */
public class Result {

    private int code;
    private Object detail;

    public Result() {
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, Object detail) {
        this.code = code;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
