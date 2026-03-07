package com.kody.coinsec.backend.module.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author Kody
 * @since 2026-03-07
 */
@Data
@Entity
@Table(name = "categories", schema = "public")
public class Category {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_type", nullable = false, length = 100)
    private String categoryType;

    @ColumnDefault("''")
    @Column(name = "icon_class", nullable = false)
    private String iconClass;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;


}