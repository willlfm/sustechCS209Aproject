package com.example.analyzer.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private boolean success;

    /**
     * 响应数据。
     */
    @JsonInclude
    private T data;

    /**
     * 错误类型，用于进一步描述错误。
     */
    private String errorType;

    /**
     * 构建成功响应。
     *
     * @param <T>  数据类型
     * @return 响应体实例
     */
    public static <T> ResponseDTO<T> success(T data) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setCode(200);
        responseDTO.setSuccess(true);
        responseDTO.setData(data);
        responseDTO.setErrorType(null);
        return responseDTO;
    }

    /**
     * 构建错误响应。
     *
     * @param errorType 错误类型
     * @param <T>       数据类型
     * @return 响应体实例
     */
    public static <T> ResponseDTO<T> failure(String errorType) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setCode(500);
        responseDTO.setSuccess(false);
        responseDTO.setData(null);
        responseDTO.setErrorType(errorType);
        return responseDTO;
    }
}