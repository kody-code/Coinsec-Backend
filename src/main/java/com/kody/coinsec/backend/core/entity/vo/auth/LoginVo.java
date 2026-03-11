package com.kody.coinsec.backend.core.entity.vo.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录返回
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Data
@Accessors(chain = true)
@Schema(description = "登录返回")
public class LoginVo {

	@Schema(description = "用户名")
	private String username;

	@JsonProperty("token_name")
	@Schema(description = "token名称")
	private String tokenName;

	@JsonProperty("token_value")
	@Schema(description = "token值")
	private String tokenValue;
}
