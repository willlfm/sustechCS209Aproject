package com.example.analyzer.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 通用响应体类，用于封装API响应数据。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

    /**
     * 响应状态码，200表示成功，其他表示错误。
     */
    private int code;

    /**
     * 响应消息。
     */
    private String message;

    /**
     * 响应数据。
     */
    @JsonInclude
    private T data;

//    /**
//     * 错误类型，用于进一步描述错误。
//     */
//    private String errorType;

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(HttpStatus.OK.value(), "success", data);
    }

    public static <T> ResponseDTO<T> fail(String message) {
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }
}