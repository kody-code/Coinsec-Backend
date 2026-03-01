package com.kody.coinsec.backend.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * Web配置类
 * 配置跨域、拦截器等Web相关设置
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Configuration
@ConditionalOnProperty(name = "app.api.test-mode", havingValue = "true", matchIfMissing = false)
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}