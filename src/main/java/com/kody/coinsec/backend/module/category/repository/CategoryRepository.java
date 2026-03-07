package com.kody.coinsec.backend.module.category.repository;

import com.kody.coinsec.backend.module.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <p>
 * 分类仓库
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
