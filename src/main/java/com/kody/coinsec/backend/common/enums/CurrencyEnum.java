package com.kody.coinsec.backend.common.enums;

import lombok.Getter;

/**
 * <p>
 * 货币类型枚举
 * 对应数据库中的currency_enum枚举类型
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum CurrencyEnum {
    
    /**
     * 人民币
     */
    CNY("CNY", "人民币"),
    
    /**
     * 美元
     */
    USD("USD", "美元"),
    
    /**
     * 欧元
     */
    EUR("EUR", "欧元"),
    
    /**
     * 日元
     */
    JPY("JPY", "日元");

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
    CurrencyEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码获取枚举值
     * 
     * @param code 编码
     * @return 对应的枚举值，未找到返回null
     */
    public static CurrencyEnum fromCode(String code) {
        for (CurrencyEnum currency : values()) {
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }

    /**
     * 获取默认货币类型（人民币）
     * 
     * @return 默认货币类型
     */
    public static CurrencyEnum getDefault() {
        return CNY;
    }

    /**
     * 判断是否为主要货币类型
     * 
     * @return true表示是主要货币类型
     */
    public boolean isMajorCurrency() {
        return this == CNY || this == USD || this == EUR;
    }

    /**
     * 获取货币符号
     * 
     * @return 对应的货币符号
     */
    public String getSymbol() {
        switch (this) {
            case CNY:
                return "¥";
            case USD:
                return "$";
            case EUR:
                return "€";
            case JPY:
                return "¥";
            default:
                return this.code;
        }
    }
}