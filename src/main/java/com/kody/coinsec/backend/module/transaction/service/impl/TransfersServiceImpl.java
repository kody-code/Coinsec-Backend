package com.kody.coinsec.backend.module.transaction.service.impl;

import com.kody.coinsec.backend.module.transaction.entity.Transfers;
import com.kody.coinsec.backend.module.transaction.mapper.TransfersMapper;
import com.kody.coinsec.backend.module.transaction.service.TransfersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 转账记录表，存储账户间的转账详情 服务实现类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Service
public class TransfersServiceImpl extends ServiceImpl<TransfersMapper, Transfers> implements TransfersService {

}
