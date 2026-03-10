package com.kody.coinsec.backend.core.repository;

import com.kody.coinsec.backend.core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 分类仓库
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
