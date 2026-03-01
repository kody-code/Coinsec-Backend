package com.kody.coinsec.backend.module.transaction.entity;

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
 * 转账记录表，存储账户间的转账详情
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
@Setter
@ToString
@TableName("transfers")
@Accessors(chain = true)
public class Transfers implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 转账记录唯一标识符，使用UUID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private UUID id;

    /**
     * 关联的交易记录ID
     */
    @TableField("transaction_id")
    private UUID transactionId;

    /**
     * 转出账户ID
     */
    @TableField("from_account_id")
    private UUID fromAccountId;

    /**
     * 转入账户ID
     */
    @TableField("to_account_id")
    private UUID toAccountId;

    /**
     * 转账金额
     */
    @TableField("transfer_amount")
    private BigDecimal transferAmount;

    /**
     * 转账记录创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
