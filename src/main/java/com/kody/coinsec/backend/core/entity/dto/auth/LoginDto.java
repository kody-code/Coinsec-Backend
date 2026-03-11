package com.kody.coinsec.backend.core.entity.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录参数
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Data
@Accessors(chain = true)
@Schema(description = "登录参数")
public class LoginDto {

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	@Schema(description = "用户名", example = "admin")
	@Size(min = 4, max = 20, message = "用户名长度必须在4到20个字符之间")
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	@Schema(description = "密码", example = "123456")
	@Size(min = 6, max = 20, message = "密码长度必须在6到20个字符之间")
	private String password;
}
