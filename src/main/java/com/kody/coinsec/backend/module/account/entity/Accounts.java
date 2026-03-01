package com.kody.coinsec.backend.module.account.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 账户信息表，存储用户的各种财务账户
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
@Setter
@ToString
@TableName("accounts")
@Accessors(chain = true)
public class Accounts implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 账户唯一标识符，使用UUID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private UUID id;

    /**
     * 所属用户ID，外键关联users表
     */
    @TableField("user_id")
    private UUID userId;

    /**
     * 账户名称，如"支付宝-日常"、"现金钱包"等
     */
    @TableField("name")
    private String name;

    /**
     * 账户类型：现金、银行卡、数字钱包等
     */
    @TableField("account_type")
    private String accountType;

    /**
     * 账户当前余额
     */
    @TableField("balance")
    private BigDecimal balance;

    /**
     * 账户货币类型，默认人民币
     */
    @TableField("currency")
    private String currency;

    /**
     * Font Awesome图标类名，用于前端显示
     */
    @TableField("icon_class")
    private String iconClass;

    /**
     * 账户是否激活状态
     */
    @TableField("is_active")
    private Boolean active;

    /**
     * 账户创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 账户信息最后更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
