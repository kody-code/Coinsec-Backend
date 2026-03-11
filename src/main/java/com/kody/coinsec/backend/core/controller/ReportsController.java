package com.kody.coinsec.backend.core.controller;

import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.core.exception.NotImplementedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 交易记录管理接口
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
@Tag(name = "报表", description = "报表接口")
public class ReportsController {

	/**
	 * <p>
	 * 获取账户余额
	 * </p>
	 *
	 * @param id 账户ID
	 *
	 * @return 账户余额
	 */
	@GetMapping("/balance/{id}")
	@Operation(summary = "获取账户余额")
	public ApiResponse<Void> balance(@PathVariable Long id) {
		throw new NotImplementedException(Map.of("id", id));
	}

	/**
	 * <p>
	 * 获取账户月度统计
	 * </p>
	 *
	 * @param id    账户ID
	 * @param year  年
	 * @param month 月
	 *
	 * @return 账户流水
	 */
	@GetMapping("/monthly/{id}/{year}/{month}")
	@Operation(summary = "获取账户流水")
	public ApiResponse<Void> monthly(
			@PathVariable Long id,
			@PathVariable Integer year,
			@PathVariable Integer month) {
		throw new NotImplementedException(Map.of(
				"id", id,
				"year", year,
				"month", month
		));
	}

	/**
	 * <p>
	 * 获取账户趋势
	 * </p>
	 *
	 * @param id 账户ID
	 *
	 * @return 账户趋势
	 */
	@GetMapping("/trend/{id}")
	public ApiResponse<Void> trend(@PathVariable Long id) {
		throw new NotImplementedException(Map.of("id", id));
	}
}
