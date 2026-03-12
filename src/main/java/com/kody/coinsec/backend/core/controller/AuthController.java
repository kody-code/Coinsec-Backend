package com.kody.coinsec.backend.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.common.response.ResponseCode;
import com.kody.coinsec.backend.common.utils.PasswordUtil;
import com.kody.coinsec.backend.core.entity.dto.auth.LoginDto;
import com.kody.coinsec.backend.core.entity.vo.auth.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 认证接口
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "认证接口", description = "用户注册、登录、信息管理等相关接口")
public class AuthController {

	@Value("${app.username}")
	private String username;

	@Value("${app.password}")
	private String password;

	/**
	 * 登录
	 *
	 * @param loginDto 登录参数
	 *
	 * @return 登录结果
	 */
	@PostMapping("/login")
	@Operation(summary = "登录", description = "用户登录接口")
	public ApiResponse<LoginVo> login(@Valid @RequestBody LoginDto loginDto) {
		log.info("登录参数：{}", loginDto);
		if (username.equals(loginDto.getUsername()) && PasswordUtil.verify(loginDto.getPassword(), password)) {
			StpUtil.login(0);
			return ApiResponse.success(
					new LoginVo()
							.setUsername(loginDto.getUsername())
							.setTokenName(StpUtil.getTokenName())
							.setTokenValue(StpUtil.getTokenValue())
			);
		} else {
			return ApiResponse.error(ResponseCode.LOGIN_FAILED);
		}
	}

	/**
	 * 登出
	 *
	 * @return 登出结果
	 */
	@PostMapping("/logout")
	@Operation(summary = "登出", description = "用户登出接口")
	public ApiResponse<Void> logout() {
		StpUtil.logout();
		return ApiResponse.success();
	}
}
