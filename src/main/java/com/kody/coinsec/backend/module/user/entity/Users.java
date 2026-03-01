package com.kody.coinsec.backend.module.user.entity;

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
 * 用户信息表，存储系统用户的基本信息和认证数据
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
@Setter
@ToString
@TableName("users")
@Accessors(chain = true)
public class Users implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识符，使用UUID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private UUID id;

    /**
     * 用户名，用于登录，唯一
     */
    @TableField("username")
    private String username;

    /**
     * 用户邮箱，可选，唯一
     */
    @TableField("email")
    private String email;

    /**
     * 加密后的用户密码
     */
    @TableField("password_hash")
    private String passwordHash;

    /**
     * 用户创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 用户信息最后更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 用户最后登录时间
     */
    @TableField("last_login")
    private LocalDateTime lastLogin;
}
