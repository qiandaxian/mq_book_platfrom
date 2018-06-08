package com.cic.config.dao;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 */
public class Result {
    private Head head;
    private Object data;

    public Head getHead() {
        return head;
    }

    public Result setHead(Head head) {
        this.head = head;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
