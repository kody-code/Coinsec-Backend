package com.kody.coinsec.backend.module.record.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 记录接口
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/record")
@Tag(name = "记录接口", description = "记录接口")
public class RecordController {
}
