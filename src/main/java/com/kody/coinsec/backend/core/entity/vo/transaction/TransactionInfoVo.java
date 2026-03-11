package com.kody.coinsec.backend.core.entity.vo.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * <p>
 * 交易信息
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-11
 */
@Data
@Accessors(chain = true)
@Schema(example = "交易信息")
public class TransactionInfoVo {


	@Schema(example = "1")
	private Long id;

	@Schema(example = "1.00")
	private BigDecimal amount;

	@Schema(example = "EXPENSE | TRANSFER | INCOME")
	private String type;

	@Schema(example = "AiPay")
	private String account;

	@Schema(example = "AiPay")
	private String toAccount;

	@Schema(example = "Food")
	private String category;

	@Schema(example = "2026-03-11T00:00:00Z")
	private Instant occurredAt;

	@Schema(example = "Food")
	private String remark;
}
