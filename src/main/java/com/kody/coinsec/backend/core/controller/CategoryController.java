package com.kody.coinsec.backend.core.controller;

import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.core.entity.dto.category.CategoryDto;
import com.kody.coinsec.backend.core.entity.vo.category.CategoryInfoVo;
import com.kody.coinsec.backend.core.exception.NotImplementedException;
import com.kody.coinsec.backend.core.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 分类管理
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@Tag(name = "分类管理", description = "分类管理接口, 创建、修改、删除、查询等")
public class CategoryController {

	/**
	 * 分类服务
	 */
	private final CategoryService categoryService;

	/**
	 * 创建分类
	 *
	 * @param createCategoryDto 创建分类参数
	 *
	 * @return 创建成功的分类信息
	 */
	@PostMapping
	@Operation(summary = "创建分类")
	public ApiResponse<CategoryInfoVo> createCategory(
			@Valid @RequestBody CategoryDto createCategoryDto) {
		throw new NotImplementedException(Map.of());
	}

	/**
	 * 查询所有分类
	 *
	 * @return 分类信息列表
	 */
	@GetMapping("/tree")
	@Operation(summary = "查询所有分类")
	public ApiResponse<CategoryInfoVo> getAllCategoryInfo() {
		throw new NotImplementedException(Map.of());
	}

	/**
	 * 查询分类
	 *
	 * @param id 分类ID
	 *
	 * @return 分类信息
	 */
	@GetMapping("/{id}")
	@Operation(summary = "查询分类")
	public ApiResponse<CategoryInfoVo> getCategoryInfo(@PathVariable Long id) {
		throw new NotImplementedException(Map.of());
	}

	/**
	 * 修改分类
	 *
	 * @param id          分类ID
	 * @param categoryDto 修改分类参数
	 *
	 * @return 修改成功的分类信息
	 */
	@PutMapping("/{id}")
	@Operation(summary = "修改分类")
	public ApiResponse<CategoryInfoVo> updateCategory(
			@PathVariable Long id,
			@Valid @RequestBody CategoryDto categoryDto
	) {
		throw new NotImplementedException(Map.of());
	}

	/**
	 * 删除分类
	 *
	 * @param id 分类ID
	 *
	 * @return 删除成功的分类信息
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "删除分类")
	public ApiResponse<CategoryInfoVo> deleteCategory(@PathVariable Long id) {
		throw new NotImplementedException(Map.of());
	}
}
