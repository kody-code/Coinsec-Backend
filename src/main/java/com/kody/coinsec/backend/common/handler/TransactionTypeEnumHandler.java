package com.kody.coinsec.backend.common.handler;

import com.kody.coinsec.backend.common.enums.TransactionTypeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * <p>
 * 交易类型枚举处理器
 * 处理transaction_type_enum数据库类型与TransactionTypeEnum Java枚举的转换
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@MappedTypes(TransactionTypeEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TransactionTypeEnumHandler extends BaseEnumTypeHandler<TransactionTypeEnum> {
    
    /**
     * 构造函数
     */
    public TransactionTypeEnumHandler() {
        super(TransactionTypeEnum.class);
    }
}