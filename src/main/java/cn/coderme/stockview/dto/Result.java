package cn.coderme.stockview.dto;

import lombok.Getter;

/**
 * 返回 json 基类
 * Created By coderme
 * Date:2018/6/20
 * Time:11:20
 */
public class Result<T> {

    public static final Integer SUCCESS = 200;
    public static final Integer FAIL = 500;

    private Integer code = SUCCESS;
    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}