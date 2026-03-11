package com.kody.coinsec.backend.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(max = 50)
	@NotNull
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@Size(max = 10)
	@NotNull
	@Column(name = "type", nullable = false, length = 10)
	private String type;

	@Size(max = 100)
	@Column(name = "icon", length = 100)
	private String icon;

	@ColumnDefault("0")
	@Column(name = "sort_order")
	private Integer sortOrder;

	@ColumnDefault("true")
	@Column(name = "is_active")
	private Boolean isActive;

	@ColumnDefault("now()")
	@Column(name = "created_at")
	private Instant createdAt;


}