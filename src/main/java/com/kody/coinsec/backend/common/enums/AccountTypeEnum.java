package com.kody.coinsec.backend.common.enums;

import lombok.Getter;

/**
 * <p>
 * 账户类型枚举
 * 对应数据库中的account_type_enum枚举类型
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum AccountTypeEnum {
    
    /**
     * 现金账户
     */
    CASH("cash", "现金"),
    
    /**
     * 银行卡账户
     */
    BANK_CARD("bank_card", "银行卡"),
    
    /**
     * 数字钱包账户（包含支付宝、微信等）
     */
    DIGITAL_WALLET("digital_wallet", "数字钱包"),
    
    /**
     * 信用卡账户
     */
    CREDIT_CARD("credit_card", "信用卡"),
    
    /**
     * 投资账户
     */
    INVESTMENT("investment", "投资账户"),
    
    /**
     * 其他类型账户
     */
    OTHER("other", "其他");

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
    AccountTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码获取枚举值
     * 
     * @param code 编码
     * @return 对应的枚举值，未找到返回null
     */
    public static AccountTypeEnum fromCode(String code) {
        for (AccountTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 判断是否为现金类账户
     * 
     * @return true表示是现金类账户
     */
    public boolean isCashType() {
        return this == CASH;
    }

    /**
     * 判断是否为银行卡类账户
     * 
     * @return true表示是银行卡类账户
     */
    public boolean isBankCardType() {
        return this == BANK_CARD || this == CREDIT_CARD;
    }

    /**
     * 判断是否为数字钱包类账户
     * 
     * @return true表示是数字钱包类账户
     */
    public boolean isDigitalWalletType() {
        return this == DIGITAL_WALLET;
    }
}