package com.kody.coinsec.backend.common.handler;

import com.kody.coinsec.backend.common.enums.CategoryTypeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * <p>
 * 分类类型枚举处理器
 * 处理category_type_enum数据库类型与CategoryTypeEnum Java枚举的转换
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@MappedTypes(CategoryTypeEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CategoryTypeEnumHandler extends BaseEnumTypeHandler<CategoryTypeEnum> {
    
    /**
     * 构造函数
     */
    public CategoryTypeEnumHandler() {
        super(CategoryTypeEnum.class);
    }
}