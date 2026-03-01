package com.kody.coinsec.backend.module.category.service.impl;

import com.kody.coinsec.backend.module.category.entity.Categories;
import com.kody.coinsec.backend.module.category.mapper.CategoriesMapper;
import com.kody.coinsec.backend.module.category.service.CategoriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交易分类表，存储系统预设的收支分类 服务实现类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements CategoriesService {

}
