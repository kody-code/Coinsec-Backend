package com.kody.coinsec.backend.module.record.entity;

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
 * 记录表
 * </p>
 *
 * @author Kody
 * @since 2026-03-07
 */
@Data
@Entity
@Table(name = "records", schema = "public")
public class Record {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "record_type", nullable = false, length = 100)
    private String recordType;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "remark", length = Integer.MAX_VALUE)
    private String remark;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "occurrence_time", nullable = false)
    private Instant occurrenceTime;

    @ColumnDefault("false")
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;
}