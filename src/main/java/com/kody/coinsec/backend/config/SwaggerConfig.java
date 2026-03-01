package com.kody.coinsec.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Swagger 配置类
 * 配置API文档的元信息
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Configuration
public class SwaggerConfig {

    /**
     * 配置OpenAPI信息
     */
    @Bean
    public OpenAPI coinsecOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CoinSec 个人记账系统 API")
                        .description("CoinSec 个人记账系统的后端API文档")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Kody")
                                .email("wangzhaojie219@163.com")
                                .url("https://github.com/kody-code/Coinsec-Backend"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}