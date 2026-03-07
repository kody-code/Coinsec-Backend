package com.kody.coinsec.backend.module.account.repository;

import com.kody.coinsec.backend.module.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <p>
 * 账户仓库
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
