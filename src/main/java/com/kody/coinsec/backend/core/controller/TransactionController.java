package com.kody.coinsec.backend.core.controller;

import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.core.entity.dto.transaction.CreateTransactionDto;
import com.kody.coinsec.backend.core.entity.vo.transaction.TransactionInfoVo;
import com.kody.coinsec.backend.core.exception.NotImplementedException;
import com.kody.coinsec.backend.core.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 交易记录控制器
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
@Tag(name = "交易记录管理接口", description = "交易记录管理接口, 包含创建、查询、更新、删除等操作")
public class TransactionController {

	/**
	 * 交易记录服务
	 */
	private final TransactionService transactionService;

	/**
	 * 创建交易记录
	 *
	 * @param createTransactionDto 创建交易记录参数
	 *
	 * @return 创建结果
	 */
	@PostMapping
	@Operation(summary = "创建交易记录")
	public ApiResponse<TransactionInfoVo> createTransactions(
			@Valid @RequestBody CreateTransactionDto createTransactionDto
	) {
		throw new NotImplementedException(Map.of(
				"amount", createTransactionDto.getAmount(),
				"type", createTransactionDto.getType(),
				"accountId", createTransactionDto.getAccountId(),
				"toAccountId", createTransactionDto.getToAccountId(),
				"categoryId", createTransactionDto.getCategoryId(),
				"occurredAt", createTransactionDto.getOccurredAt(),
				"remark", createTransactionDto.getRemark()
		));
	}

	/**
	 * 查询所有交易记录
	 *
	 * @return 交易记录列表
	 */
	@GetMapping
	@Operation(summary = "查询所有交易记录")
	public ApiResponse<TransactionInfoVo> getAllTransactions() {
		throw new NotImplementedException(Map.of());
	}

	/**
	 * 查询指定交易记录
	 *
	 * @param id 交易记录ID
	 *
	 * @return 交易记录信息
	 */
	@GetMapping("/{id}")
	@Operation(summary = "查询指定交易记录")
	public ApiResponse<TransactionInfoVo> getTransactionById(@PathVariable Long id) {
		throw new NotImplementedException(Map.of("id", id));
	}

	/**
	 * 更新交易记录
	 *
	 * @param id                   交易记录ID
	 * @param updateTransactionDto 更新交易记录参数
	 *
	 * @return 更新结果
	 */
	@PutMapping("/{id}")
	@Operation(summary = "更新交易记录")
	public ApiResponse<TransactionInfoVo> updateTransaction(
			@PathVariable Long id,
			@Valid @RequestBody CreateTransactionDto updateTransactionDto) {
		throw new NotImplementedException(Map.of(
				"id", id,
				"amount", updateTransactionDto.getAmount(),
				"type", updateTransactionDto.getType(),
				"accountId", updateTransactionDto.getAccountId(),
				"toAccountId", updateTransactionDto.getToAccountId(),
				"categoryId", updateTransactionDto.getCategoryId(),
				"occurredAt", updateTransactionDto.getOccurredAt(),
				"remark", updateTransactionDto.getRemark()
		));
	}

	/**
	 * 删除交易记录
	 *
	 * @param id 交易记录ID
	 *
	 * @return 删除结果
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "删除交易记录")
	public ApiResponse<TransactionInfoVo> deleteTransaction(@PathVariable Long id) {
		throw new NotImplementedException(Map.of("id", id));
	}
}
