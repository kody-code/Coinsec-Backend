package com.kody.coinsec.backend.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 响应状态码枚举类
 * 定义系统中使用的所有标准响应状态码
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "响应状态码")
public enum ResponseCode {

    // ==================== 成功状态码 ====================
    /**
     * 请求成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 创建成功
     */
    CREATED(201, "创建成功"),

    /**
     * 无内容
     */
    NO_CONTENT(204, "无内容"),

    // ==================== 客户端错误码 (4xx) ====================
    /**
     * 请求参数错误
     */
    BAD_REQUEST(400, "请求参数错误"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),

    /**
     * 权限不足
     */
    FORBIDDEN(403, "权限不足"),

    /**
     * 资源不存在
     */
    NOT_FOUND(404, "资源不存在"),

    /**
     * 资源冲突
     */
    CONFLICT(409, "资源冲突"),

    // ==================== 服务器错误码 (5xx) ====================
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    // ==================== 业务错误码 (1xxxx) - 用户模块 ====================
    /**
     * 参数验证失败
     */
    VALIDATION_FAILED(10001, "参数验证失败"),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND(10002, "用户不存在"),

    /**
     * 密码错误
     */
    INVALID_PASSWORD(10003, "密码错误"),

    /**
     * 账户已被禁用
     */
    USER_DISABLED(10004, "账户已被禁用"),

    /**
     * 用户名已存在
     */
    USERNAME_EXISTS(10005, "用户名已存在"),

    /**
     * 邮箱已存在
     */
    EMAIL_EXISTS(10006, "邮箱已存在"),

    // ==================== 业务错误码 (2xxxx) - 账户模块 ====================
    /**
     * 账户不存在
     */
    ACCOUNT_NOT_FOUND(20001, "账户不存在"),

    /**
     * 余额不足
     */
    INSUFFICIENT_BALANCE(20002, "余额不足"),

    /**
     * 账户名称已存在
     */
    ACCOUNT_NAME_EXISTS(20003, "账户名称已存在"),

    /**
     * 账户已被禁用
     */
    ACCOUNT_DISABLED(20004, "账户已被禁用"),

    /**
     * 账户类型不支持
     */
    ACCOUNT_TYPE_NOT_SUPPORTED(20005, "账户类型不支持"),

    // ==================== 业务错误码 (3xxxx) - 分类模块 ====================
    /**
     * 分类不存在
     */
    CATEGORY_NOT_FOUND(30001, "分类不存在"),

    /**
     * 不能删除系统分类
     */
    SYSTEM_CATEGORY_CANNOT_DELETE(30002, "不能删除系统分类"),

    /**
     * 分类名称已存在
     */
    CATEGORY_NAME_EXISTS(30003, "分类名称已存在"),

    /**
     * 分类层级过深
     */
    CATEGORY_DEPTH_EXCEEDED(30004, "分类层级过深"),

    // ==================== 业务错误码 (4xxxx) - 交易模块 ====================
    /**
     * 交易记录不存在
     */
    TRANSACTION_NOT_FOUND(40001, "交易记录不存在"),

    /**
     * 转账账户不能相同
     */
    TRANSFER_SAME_ACCOUNT(40002, "转账账户不能相同"),

    /**
     * 交易金额必须大于0
     */
    TRANSACTION_AMOUNT_INVALID(40003, "交易金额必须大于0"),

    /**
     * 交易日期不能_future
     */
    TRANSACTION_DATE_INVALID(40004, "交易日期无效"),

    /**
     * 分类与交易类型不匹配
     */
    CATEGORY_TYPE_MISMATCH(40005, "分类与交易类型不匹配"),

    // ==================== 业务错误码 (5xxxx) - 系统模块 ====================
    /**
     * 系统维护中
     */
    SYSTEM_MAINTENANCE(50001, "系统维护中"),

    /**
     * 数据库操作失败
     */
    DATABASE_OPERATION_FAILED(50002, "数据库操作失败"),

    /**
     * 文件上传失败
     */
    FILE_UPLOAD_FAILED(50003, "文件上传失败");

    /**
     * 状态码
     */
    @Schema(description = "状态码")
    private Integer code;

    /**
     * 状态消息
     */
    @Schema(description = "状态消息")
    private String message;

    /**
     * 获取状态码
     * 
     * @return 状态码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取状态消息
     * 
     * @return 状态消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 根据状态码获取对应的枚举值
     * 
     * @param code 状态码
     * @return 对应的枚举值，未找到返回null
     */
    public static ResponseCode fromCode(Integer code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }

    /**
     * 判断是否为成功状态码
     * 
     * @return true表示成功状态码
     */
    public boolean isSuccess() {
        return this.code >= 200 && this.code < 300;
    }

    /**
     * 判断是否为客户端错误码
     * 
     * @return true表示客户端错误码
     */
    public boolean isClientError() {
        return this.code >= 400 && this.code < 500;
    }

    /**
     * 判断是否为服务器错误码
     * 
     * @return true表示服务器错误码
     */
    public boolean isServerError() {
        return this.code >= 500 && this.code < 600;
    }

    /**
     * 判断是否为业务错误码
     * 
     * @return true表示业务错误码
     */
    public boolean isBusinessError() {
        return this.code >= 10000 && this.code < 60000;
    }
}