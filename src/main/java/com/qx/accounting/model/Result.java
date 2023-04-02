package com.qx.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/31 17:42
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public Result success(Object data) {
        this.code = 200;
        this.msg = "请求成功";
        this.data = data;
        return this;
    }

    public Result success() {
        this.code = 200;
        this.msg = "请求成功";
        return this;
    }

    public Result error() {
        this.code = 500;
        this.msg = "服务器错误";
        return this;
    }

    public Result error(String msg) {
        this.code = 500;
        this.msg = msg;
        return this;
    }

}

