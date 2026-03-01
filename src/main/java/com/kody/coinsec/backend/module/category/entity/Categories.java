package com.kody.coinsec.backend.module.category.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 交易分类表，存储系统预设的收支分类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("categories")
public class Categories implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类唯一标识符，使用UUID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private UUID id;

    /**
     * 分类名称，如"工资"、"餐饮"等
     */
    @TableField("name")
    private String name;

    /**
     * 分类类型：收入(income)或支出(expense)
     */
    @TableField("category_type")
    private String categoryType;

    /**
     * 父分类ID，用于构建分类层级
     */
    @TableField("parent_id")
    private Object parentId;

    /**
     * Font Awesome图标类名
     */
    @TableField("icon_class")
    private String iconClass;

    /**
     * 分类显示颜色，十六进制格式
     */
    @TableField("color_hex")
    private String colorHex;

    /**
     * 分类排序序号
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * 是否为系统预设分类
     */
    @TableField("is_system")
    private Boolean system;

    /**
     * 分类创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
