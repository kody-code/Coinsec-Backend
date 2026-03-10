package com.kody.coinsec.backend.core.service;

import com.kody.coinsec.backend.core.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户服务
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

	/**
	 * 账户仓库
	 */
	private final AccountRepository accountRepository;
}
