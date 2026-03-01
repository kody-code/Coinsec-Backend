package com.kody.coinsec.backend.common.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * PostgreSQL枚举类型处理器基类
 * 用于处理数据库枚举类型与Java枚举类型的相互转换
 * </p>
 *
 * @param <E> 枚举类型
 * @author Kody
 * @since 2026-03-01
 */
public abstract class BaseEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    /**
     * 枚举类型Class对象
     */
    private final Class<E> type;

    /**
     * 构造函数
     * 
     * @param type 枚举类型Class对象
     * @throws IllegalArgumentException 当type为null时抛出
     */
    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    /**
     * 设置非空参数
     * 
     * @param ps PreparedStatement对象
     * @param i 参数位置
     * @param parameter 参数值
     * @param jdbcType JDBC类型
     * @throws SQLException SQL异常
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setString(i, parameter.name());
        } else {
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    /**
     * 根据列名获取可空结果
     * 
     * @param rs ResultSet对象
     * @param columnName 列名
     * @return 枚举对象
     * @throws SQLException SQL异常
     */
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value != null ? getEnum(value) : null;
    }

    /**
     * 根据列索引获取可空结果
     * 
     * @param rs ResultSet对象
     * @param columnIndex 列索引
     * @return 枚举对象
     * @throws SQLException SQL异常
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value != null ? getEnum(value) : null;
    }

    /**
     * 根据存储过程参数获取可空结果
     * 
     * @param cs CallableStatement对象
     * @param columnIndex 参数索引
     * @return 枚举对象
     * @throws SQLException SQL异常
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value != null ? getEnum(value) : null;
    }

    /**
     * 根据字符串值获取枚举对象
     * 
     * @param value 字符串值
     * @return 枚举对象
     */
    private E getEnum(String value) {
        try {
            return Enum.valueOf(type, value);
        } catch (IllegalArgumentException e) {
            // 如果枚举值不存在，返回null而不是抛出异常
            return null;
        }
    }
}