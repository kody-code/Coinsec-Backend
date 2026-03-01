package com.kody.coinsec.backend.common.handler;

import com.kody.coinsec.backend.common.enums.AccountTypeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * <p>
 * 账户类型枚举处理器
 * 处理account_type_enum数据库类型与AccountTypeEnum Java枚举的转换
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@MappedTypes(AccountTypeEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class AccountTypeEnumHandler extends BaseEnumTypeHandler<AccountTypeEnum> {
    
    /**
     * 构造函数
     */
    public AccountTypeEnumHandler() {
        super(AccountTypeEnum.class);
    }
}