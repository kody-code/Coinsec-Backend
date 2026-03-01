package com.kody.coinsec.backend.common.handler;

import com.kody.coinsec.backend.common.enums.CurrencyEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * <p>
 * 货币类型枚举处理器
 * 处理currency_enum数据库类型与CurrencyEnum Java枚举的转换
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@MappedTypes(CurrencyEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CurrencyEnumHandler extends BaseEnumTypeHandler<CurrencyEnum> {
    
    /**
     * 构造函数
     */
    public CurrencyEnumHandler() {
        super(CurrencyEnum.class);
    }
}