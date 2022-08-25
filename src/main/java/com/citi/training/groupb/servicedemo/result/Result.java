package com.citi.training.groupb.servicedemo.result;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Result<T> {
    // 响应代码
    private Integer code;
    // 响应消息
    private String message;
    // 响应结果数据
    private T data;

    public Result() {}

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}