package com.kody.coinsec.backend.core.entity.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * <p>
 * 创建交易DTO
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-11
 */
@Data
@Accessors(chain = true)
@Schema(description = "创建交易DTO")
public class CreateTransactionDto {

	@Schema(description = "交易金额")
	@NotNull(message = "交易金额不能为空")
	private BigDecimal amount;

	@Schema(description = "交易类型")
	@NotNull(message = "交易类型不能为空")
	private String type;

	@JsonProperty("account_id")
	@Schema(description = "账户ID")
	@NotNull(message = "账户ID不能为空")
	private Long accountId;

	@JsonProperty("to_account_id")
	@Schema(description = "转账账户ID")
	@NotNull(message = "转账账户ID不能为空")
	private Long toAccountId;

	@JsonProperty("category_id")
	@Schema(description = "分类ID")
	@NotNull(message = "分类ID不能为空")
	private Long categoryId;

	@JsonProperty("occurred_at")
	@Schema(description = "发生时间")
	@NotNull(message = "发生时间不能为空")
	private Instant occurredAt;

	@Schema(description = "备注")
	private String remark;
}
