package com.kody.coinsec.backend.module.transaction.service.impl;

import com.kody.coinsec.backend.module.transaction.entity.Transactions;
import com.kody.coinsec.backend.module.transaction.mapper.TransactionsMapper;
import com.kody.coinsec.backend.module.transaction.service.TransactionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交易记录表，存储用户的所有收支交易 服务实现类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Service
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions> implements TransactionsService {

}
