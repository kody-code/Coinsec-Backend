package com.kody.coinsec.backend.module.record.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账单服务
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "record")
public class RecordService {
}
