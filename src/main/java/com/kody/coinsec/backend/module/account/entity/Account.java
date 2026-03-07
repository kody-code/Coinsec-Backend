package com.kody.coinsec.backend.module.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author Kody
 * @since 2026-03-07
 */
@Data
@Entity
@Table(name = "accounts", schema = "public")
public class Account {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @ColumnDefault("0.0")
    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "account_type", nullable = false, length = 100)
    private String accountType;

    @ColumnDefault("false")
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ColumnDefault("''")
    @Column(name = "icon_class", nullable = false)
    private String iconClass;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;


}