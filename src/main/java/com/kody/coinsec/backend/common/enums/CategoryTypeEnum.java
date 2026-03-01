package com.kody.coinsec.backend.common.enums;

import lombok.Getter;

/**
 * <p>
 * 分类类型枚举
 * 对应数据库中的category_type_enum枚举类型
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum CategoryTypeEnum {
    
    /**
     * 收入分类
     */
    INCOME("income", "收入"),
    
    /**
     * 支出分类
     */
    EXPENSE("expense", "支出");

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
    CategoryTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码获取枚举值
     * 
     * @param code 编码
     * @return 对应的枚举值，未找到返回null
     */
    public static CategoryTypeEnum fromCode(String code) {
        for (CategoryTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 判断是否为收入类型
     * 
     * @return true表示是收入类型
     */
    public boolean isIncome() {
        return this == INCOME;
    }

    /**
     * 判断是否为支出类型
     * 
     * @return true表示是支出类型
     */
    public boolean isExpense() {
        return this == EXPENSE;
    }
}