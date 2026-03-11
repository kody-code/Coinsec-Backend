package com.kody.coinsec.backend.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * <p>
 * 交易
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "amount", nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;

	@Size(max = 10)
	@NotNull
	@Column(name = "type", nullable = false, length = 10)
	private String type;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	@JoinColumn(name = "to_account_id")
	private Account toAccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	@JoinColumn(name = "category_id")
	private Category category;

	@NotNull
	@ColumnDefault("now()")
	@Column(name = "occurred_at", nullable = false)
	private Instant occurredAt;

	@Size(max = 200)
	@Column(name = "remark", length = 200)
	private String remark;

	@ColumnDefault("false")
	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@ColumnDefault("now()")
	@Column(name = "created_at")
	private Instant createdAt;


}