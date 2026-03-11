package com.kody.coinsec.backend.core.entity.vo.category;

import com.kody.coinsec.backend.core.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分类信息
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Data
@Accessors(chain = true)
@Schema(description = "分类信息")
public class CategoryInfoVo {

	@Schema(description = "分类ID")
	private Long id;

	@Schema(description = "分类名称")
	private String name;

	@Schema(description = "父级分类")
	private Category parent;

	@Schema(description = "分类类型")
	private String type;

	@Schema(description = "图标")
	private String icon;

	@JoinColumn(name = "parent_id")
	@Schema(description = "排序")
	private Integer sortOrder;
}
