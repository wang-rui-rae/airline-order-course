package com.postion.airlineorderbackend.dto;

import lombok.Data;

@Data
/**
 * 实现API返回数据的统一格式
 * 使用泛型<T>支持任意数据类型
 */
public class ApiResponse<T> {

    // 定义业务状态码（最好不要使用HTTP状态码，方便前后端自定义规则）
    // 同时，为了遵循RESTFUL风格，还需要在ApiResponse<T>外层嵌套ResponseEntity
    private int code;
    // 定义提示信息
    private String message;
    // 定义数据泛型
    private T data;

    // 预设成功响应
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(data);
        return response;
    }

    // 预设失败响应
    public static <T> ApiResponse<T> error(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}
