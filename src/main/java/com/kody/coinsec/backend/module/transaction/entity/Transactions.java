package com.kody.coinsec.backend.module.transaction.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 交易记录表，存储用户的所有收支交易
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("transactions")
public class Transactions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 交易唯一标识符，使用UUID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private UUID id;

    /**
     * 所属用户ID，外键关联users表
     */
    @TableField("user_id")
    private UUID userId;

    /**
     * 相关账户ID，外键关联accounts表
     */
    @TableField("account_id")
    private UUID accountId;

    /**
     * 交易分类ID，外键关联categories表
     */
    @TableField("category_id")
    private UUID categoryId;

    /**
     * 交易类型：收入、支出、转账
     */
    @TableField("transaction_type")
    private String transactionType;

    /**
     * 交易金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 交易描述说明
     */
    @TableField("description")
    private String description;

    /**
     * 交易发生日期
     */
    @TableField("transaction_date")
    private LocalDate transactionDate;

    /**
     * 交易记录创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 交易记录最后更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
