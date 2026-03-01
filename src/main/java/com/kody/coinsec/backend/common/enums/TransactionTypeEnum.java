package com.kody.coinsec.backend.common.enums;

import lombok.Getter;

/**
 * <p>
 * 交易类型枚举
 * 对应数据库中的transaction_type_enum枚举类型
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum TransactionTypeEnum {
    
    /**
     * 收入类型
     */
    INCOME("income", "收入"),
    
    /**
     * 支出类型
     */
    EXPENSE("expense", "支出"),
    
    /**
     * 转账类型
     */
    TRANSFER("transfer", "转账");

    /**
     * 枚举编码 - 对应数据库存储值
     */
    private final String code;
    
    /**
     * 枚举描述 - 用于界面显示
     */
    private final String description;

    /**
     * 构造函数
     * 
     * @param code 枚举编码
     * @param description 枚举描述
     */
    TransactionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码获取枚举值
     * 
     * @param code 编码
     * @return 对应的枚举值，未找到返回null
     */
    public static TransactionTypeEnum fromCode(String code) {
        for (TransactionTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}