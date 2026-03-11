package com.kody.coinsec.backend.core.entity.vo.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 账户信息
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Data
@Accessors(chain = true)
@Schema(description = "账户信息")
public class AccountInfoVo {

	/**
	 * 账户ID
	 */
	@Schema(description = "账户ID", example = "1")
	private Long id;

	/**
	 * 账户名称
	 */
	@Schema(description = "账户名称", example = "账户名称")
	private String name;

	/**
	 * 账户类型
	 */
	@Schema(description = "账户类型", example = "账户类型")
	private String type;

	/**
	 * 初始余额
	 */
	@Schema(description = "初始余额", example = "100.00")
	private BigDecimal initialBalance;

	/**
	 * 币种
	 */
	@Schema(description = "币种", example = "CNY")
	private String currency;
}
