package com.kody.coinsec.backend.module.category.controller;

import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 分类管理控制器
 * 处理收支分类的增删改查等API接口
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "分类管理", description = "收支分类相关API接口")
public class CategoryController {

    /**
     * 创建分类
     */
    @PostMapping
    @Operation(summary = "创建分类", description = "创建新的收支分类")
    public ApiResponse<Void> createCategory(@RequestBody CreateCategoryRequest request) {
        log.info("创建分类请求: name={}", request.getName());
        return ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, "功能待实现");
    }

    /**
     * 查询分类列表
     */
    @GetMapping
    @Operation(summary = "查询分类列表", description = "获取分类列表")
    public ApiResponse<Void> getCategoryList(
            @Parameter(description = "分类类型") @RequestParam(required = false) String categoryType,
            @Parameter(description = "是否系统分类") @RequestParam(required = false) Boolean isSystem) {
        log.info("查询分类列表请求: categoryType={}, isSystem={}", categoryType, isSystem);
        return ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, "功能待实现");
    }

    /**
     * 获取分类树形结构
     */
    @GetMapping("/tree")
    @Operation(summary = "获取分类树", description = "获取分类的树形结构")
    public ApiResponse<Void> getCategoryTree() {
        log.info("获取分类树请求");
        return ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, "功能待实现");
    }

    /**
     * 获取分类详情
     */
    @GetMapping("/{categoryId}")
    @Operation(summary = "获取分类详情", description = "根据ID获取分类详细信息")
    public ApiResponse<Void> getCategoryById(
            @Parameter(description = "分类ID") @PathVariable String categoryId) {
        log.info("获取分类详情请求: categoryId={}", categoryId);
        return ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, "功能待实现");
    }

    /**
     * 更新分类信息
     */
    @PutMapping("/{categoryId}")
    @Operation(summary = "更新分类信息", description = "更新指定分类的信息")
    public ApiResponse<Void> updateCategory(
            @Parameter(description = "分类ID") @PathVariable String categoryId,
            @RequestBody UpdateCategoryRequest request) {
        log.info("更新分类信息请求: categoryId={}", categoryId);
        return ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, "功能待实现");
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{categoryId}")
    @Operation(summary = "删除分类", description = "删除指定分类")
    public ApiResponse<Void> deleteCategory(
            @Parameter(description = "分类ID") @PathVariable String categoryId) {
        log.info("删除分类请求: categoryId={}", categoryId);
        return ApiResponse.error(ResponseCode.INTERNAL_SERVER_ERROR, "功能待实现");
    }

    /**
     * 创建分类请求DTO
     */
    public static class CreateCategoryRequest {
        private String name;
        private String categoryType;
        private String parentId;
        private String iconClass;
        private String colorHex;

        // getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCategoryType() { return categoryType; }
        public void setCategoryType(String categoryType) { this.categoryType = categoryType; }
        public String getParentId() { return parentId; }
        public void setParentId(String parentId) { this.parentId = parentId; }
        public String getIconClass() { return iconClass; }
        public void setIconClass(String iconClass) { this.iconClass = iconClass; }
        public String getColorHex() { return colorHex; }
        public void setColorHex(String colorHex) { this.colorHex = colorHex; }
    }

    /**
     * 更新分类请求DTO
     */
    public static class UpdateCategoryRequest {
        private String name;
        private String iconClass;
        private String colorHex;
        private Integer sortOrder;

        // getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getIconClass() { return iconClass; }
        public void setIconClass(String iconClass) { this.iconClass = iconClass; }
        public String getColorHex() { return colorHex; }
        public void setColorHex(String colorHex) { this.colorHex = colorHex; }
        public Integer getSortOrder() { return sortOrder; }
        public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    }
}