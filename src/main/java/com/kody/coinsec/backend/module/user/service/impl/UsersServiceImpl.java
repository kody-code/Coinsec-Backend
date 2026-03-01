package com.kody.coinsec.backend.module.user.service.impl;

import com.kody.coinsec.backend.module.user.entity.Users;
import com.kody.coinsec.backend.module.user.mapper.UsersMapper;
import com.kody.coinsec.backend.module.user.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表，存储系统用户的基本信息和认证数据 服务实现类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
