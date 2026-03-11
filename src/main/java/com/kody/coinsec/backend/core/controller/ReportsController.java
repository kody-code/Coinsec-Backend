package com.kody.coinsec.backend.core.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
