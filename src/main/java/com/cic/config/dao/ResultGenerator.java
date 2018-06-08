package com.cic.config.dao;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    @SuppressWarnings("unused")
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";


    public static Result genSuccessResult() {

        Head head = new Head("成功",ResultCode.SUCCESS,"true");

        return new Result()
                .setHead(head);
    }

    public static Result genSuccessResult(String message) {

        Head head = new Head(message,ResultCode.SUCCESS,"true");

        return new Result()
                .setHead(head);
    }

    public static Result genSuccessResult(Object data) {
        Head head = new Head("成功",ResultCode.SUCCESS,"true");

        return new Result()
                .setHead(head)
                .setData(data);
    }

    public static Result genSuccessResult(String message ,Object data) {
        Head head = new Head(message,ResultCode.SUCCESS,"true");

        return new Result()
                .setHead(head)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        Head head = new Head(message,ResultCode.FAIL,"false");


        return new Result()
                .setHead(head);
    }
    
    public static Result genFailResult() {
        Head head = new Head("失败",ResultCode.FAIL,"false");
        return new Result()
                .setHead(head);
    }
    
    
}
