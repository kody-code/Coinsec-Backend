package com.kody.coinsec.backend.module.account.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 账户控制器
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Tag(name = "AccountController", description = "账户控制器")
public class AccountController {
}
