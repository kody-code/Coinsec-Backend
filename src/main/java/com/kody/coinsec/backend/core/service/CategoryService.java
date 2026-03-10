package com.kody.coinsec.backend.core.service;

import com.kody.coinsec.backend.core.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类服务
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

	/**
	 * 分类仓库
	 */
	private final CategoryRepository categoryRepository;
}
