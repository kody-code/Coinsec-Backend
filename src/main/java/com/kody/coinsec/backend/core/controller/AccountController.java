package com.kody.coinsec.backend.core.controller;

import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.core.entity.dto.account.CreateAccountDto;
import com.kody.coinsec.backend.core.entity.vo.account.AccountInfoVo;
import com.kody.coinsec.backend.core.exception.NotImplementedException;
import com.kody.coinsec.backend.core.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 账户管理
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@Tag(name = "账户管理", description = "账户管理接口, 包含账户信息管理")
public class AccountController {

	/**
	 * 账户服务
	 */
	private final AccountService accountService;

	/**
	 * 创建账户
	 *
	 * @param createAccountDto 创建账户参数
	 *
	 * @return 账户信息
	 */
	@PostMapping
	@Operation(summary = "创建账户", description = "创建账户")
	public ApiResponse<AccountInfoVo> createAccount(@Valid @RequestBody CreateAccountDto createAccountDto) {
		throw new NotImplementedException(Map.of(
				"name", createAccountDto.getName(),
				"type", createAccountDto.getType(),
				"initial_balance", createAccountDto.getInitialBalance(),
				"currency", createAccountDto.getCurrency(),
				"is_active", createAccountDto.isActive()
		));
	}

	/**
	 * 获取全部账户信息
	 *
	 * @return 账户信息
	 */
	@GetMapping
	@Operation(summary = "获取账户信息", description = "获取账户信息")
	public ApiResponse<AccountInfoVo> getAllAccountInfo() {
		throw new NotImplementedException(Map.of());
	}

	/**
	 * 获取账户信息
	 *
	 * @param id 账户ID
	 *
	 * @return 账户信息
	 */
	@GetMapping("/{id}")
	@Operation(summary = "获取账户信息", description = "获取账户信息")
	public ApiResponse<AccountInfoVo> getAccountInfoById(@PathVariable String id) {
		throw new NotImplementedException(Map.of(
				"id", id
		));
	}

	/**
	 * 更新账户信息
	 *
	 * @param id               账户ID
	 * @param createAccountDto 更新账户参数
	 *
	 * @return 账户信息
	 */
	@PutMapping("/{id}")
	@Operation(summary = "更新账户信息", description = "更新账户信息")
	public ApiResponse<AccountInfoVo> updateAccountInfoById(
			@PathVariable String id,
			@Valid @RequestBody CreateAccountDto createAccountDto) {
		throw new NotImplementedException(Map.of(
				"id", id,
				"name", createAccountDto.getName(),
				"type", createAccountDto.getType(),
				"initial_balance", createAccountDto.getInitialBalance(),
				"currency", createAccountDto.getCurrency(),
				"is_active", createAccountDto.isActive()
		));
	}

	/**
	 * 删除账户信息
	 *
	 * @param id 账户ID
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "删除账户信息", description = "删除账户信息")
	public ApiResponse<Void> deleteAccountInfoById(@PathVariable String id) {
		throw new NotImplementedException(Map.of(
				"id", id
		));
	}
}
