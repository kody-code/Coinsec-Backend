package com.kody.coinsec.backend.module.account.service.impl;

import com.kody.coinsec.backend.module.account.entity.Accounts;
import com.kody.coinsec.backend.module.account.mapper.AccountsMapper;
import com.kody.coinsec.backend.module.account.service.AccountsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户信息表，存储用户的各种财务账户 服务实现类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Service
public class AccountsServiceImpl extends ServiceImpl<AccountsMapper, Accounts> implements AccountsService {

}
