package com.kody.coinsec.backend.core.entity.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 创建账户参数
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Data
@Accessors(chain = true)
@Schema(description = "创建账户参数")
public class CreateAccountDto {

	/**
	 * 账户名称
	 */
	@NotBlank(message = "账户名称不能为空")
	@Schema(description = "账户名称", example = "招行卡")
	private String name;

	/**
	 * 账户类型
	 */
	@NotBlank(message = "账户类型不能为空")
	private String type;

	/**
	 * 初始余额
	 */
	@JsonProperty("initial_balance")
	@Schema(description = "初始余额", example = "0")
	private BigDecimal initialBalance = BigDecimal.ZERO;

	/**
	 * 账户币种
	 */
	@Schema(description = "账户币种", example = "CNY")
	private String currency = "CNY";

	/**
	 * 账户是否激活
	 */
	@JsonProperty("is_active")
	@Schema(description = "账户是否激活", example = "true")
	private boolean isActive;
}
