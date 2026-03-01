package com.kody.coinsec.backend.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 错误详情类
 * 用于描述具体的错误信息，包括出错字段和错误消息
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "错误详情")
public class ErrorDetail {

    /**
     * 出错字段名
     * 空字符串表示全局错误
     */
    @Schema(description = "出错字段名", example = "username")
    private String field;

    /**
     * 错误消息
     */
    @Schema(description = "错误消息", example = "用户名不能为空")
    private String message;

    /**
     * 错误码（可选）
     */
    @Schema(description = "错误码")
    private String errorCode;

    /**
     * 构造函数（字段错误）
     * 
     * @param field 字段名
     * @param message 错误消息
     */
    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * 构造函数（全局错误）
     * 
     * @param message 错误消息
     */
    public static ErrorDetail ofGlobal(String message) {
        return new ErrorDetail("", message);
    }

    /**
     * 构造函数（字段错误）
     * 
     * @param field 字段名
     * @param message 错误消息
     * @return ErrorDetail
     */
    public static ErrorDetail ofField(String field, String message) {
        return new ErrorDetail(field, message);
    }

    /**
     * 构造函数（带错误码的字段错误）
     * 
     * @param field 字段名
     * @param message 错误消息
     * @param errorCode 错误码
     * @return ErrorDetail
     */
    public static ErrorDetail ofField(String field, String message, String errorCode) {
        return new ErrorDetail(field, message, errorCode);
    }

    /**
     * 判断是否为全局错误
     * 
     * @return true表示全局错误
     */
    public boolean isGlobal() {
        return this.field == null || this.field.isEmpty();
    }

    /**
     * 判断是否为字段错误
     * 
     * @return true表示字段错误
     */
    public boolean isFieldError() {
        return !isGlobal();
    }
}