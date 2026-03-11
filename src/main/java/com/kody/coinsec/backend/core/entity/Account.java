package com.kody.coinsec.backend.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * <p>
 * 账户
 * </p>
 *
 * @author kody
 * @since 2026-03-10
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(max = 50)
	@NotNull
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Size(max = 20)
	@NotNull
	@Column(name = "type", nullable = false, length = 20)
	private String type;

	@NotNull
	@ColumnDefault("0.00")
	@Column(name = "initial_balance", nullable = false, precision = 15, scale = 2)
	private BigDecimal initialBalance;

	@Size(max = 10)
	@ColumnDefault("'CNY'")
	@Column(name = "currency", length = 10)
	private String currency;

	@ColumnDefault("true")
	@Column(name = "is_active")
	private Boolean isActive;

	@ColumnDefault("now()")
	@Column(name = "created_at")
	private Instant createdAt;

	@ColumnDefault("now()")
	@Column(name = "updated_at")
	private Instant updatedAt;


}