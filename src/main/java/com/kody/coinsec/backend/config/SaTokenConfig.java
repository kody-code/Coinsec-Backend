package com.kody.coinsec.backend.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * Sa-Token 配置类
 * 配置路由拦截器和权限验证
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                // 拦截所有接口
                .addPathPatterns("/**")
                // 放行不需要登录的接口
                .excludePathPatterns(
                        // Swagger相关接口
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/doc.html",
                        // 用户注册和登录接口
                        "/api/v1/users/register",
                        "/api/v1/users/login",
                        // 测试接口
                        "/api/test/**",
                        // 健康检查
                        "/actuator/**",
                        // 静态资源
                        "/favicon.ico",
                        "/error"
                );
    }
}