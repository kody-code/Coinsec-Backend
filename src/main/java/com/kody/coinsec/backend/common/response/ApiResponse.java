package com.kody.coinsec.backend.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 统一API响应结果封装类
 * 用于规范所有接口的返回格式，确保响应结构的一致性
 * </p>
 *
 * @param <T> 响应数据类型
 * @author Kody
 * @since 2026-03-01
 */
@Data
@Accessors(chain = true)
@Schema(description = "统一API响应结果")
public class ApiResponse<T> {

    /**
     * 响应状态码
     */
    @Schema(description = "响应状态码", example = "200")
    private Integer code;

    /**
     * 响应消息
     */
    @Schema(description = "响应消息", example = "操作成功")
    private String message;

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    /**
     * 响应时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "响应时间戳", example = "2026-03-01 16:45:30")
    private LocalDateTime timestamp;

    /**
     * 错误详情列表（仅在错误响应时使用）
     */
    @Schema(description = "错误详情列表")
    private List<ErrorDetail> errors;

    /**
     * 请求唯一标识（可选，用于调试追踪）
     */
    @Schema(description = "请求唯一标识")
    private String requestId;

    /**
     * 构造函数
     */
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 带参数构造函数
     * 
     * @param code 状态码
     * @param message 响应消息
     * @param data 响应数据
     */
    public ApiResponse(Integer code, String message, T data) {
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应（无数据）
     * 
     * @param <T> 数据类型
     * @return ApiResponse<Void>
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应（带数据）
     * 
     * @param data 响应数据
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功响应（自定义消息）
     * 
     * @param message 自定义消息
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(ResponseCode.SUCCESS.getCode(), message, null);
    }

    /**
     * 成功响应（自定义消息和数据）
     * 
     * @param message 自定义消息
     * @param data 响应数据
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 创建响应（指定状态码）
     * 
     * @param code 状态码
     * @param message 响应消息
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> of(ResponseCode code) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), null);
    }

    /**
     * 创建响应（指定状态码和数据）
     * 
     * @param code 状态码
     * @param data 响应数据
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> of(ResponseCode code, T data) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), data);
    }

    /**
     * 错误响应
     * 
     * @param code 错误码
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> error(ResponseCode code) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), null);
    }

    /**
     * 错误响应（自定义消息）
     * 
     * @param code 错误码
     * @param message 自定义错误消息
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> error(ResponseCode code, String message) {
        return new ApiResponse<>(code.getCode(), message, null);
    }

    /**
     * 错误响应（带错误详情）
     * 
     * @param code 错误码
     * @param errors 错误详情列表
     * @param <T> 数据类型
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> error(ResponseCode code, List<ErrorDetail> errors) {
        ApiResponse<T> response = new ApiResponse<>(code.getCode(), code.getMessage(), null);
        response.setErrors(errors);
        return response;
    }

    /**
     * 判断响应是否成功
     * 
     * @return true表示成功，false表示失败
     */
    @JsonIgnore
    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode().equals(this.code);
    }

    /**
     * 判断响应是否失败
     * 
     * @return true表示失败，false表示成功
     */
    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }
}