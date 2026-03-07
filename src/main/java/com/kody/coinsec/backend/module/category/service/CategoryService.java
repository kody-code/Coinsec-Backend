package com.kody.coinsec.backend.module.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类服务
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "category")
public class CategoryService {
}
