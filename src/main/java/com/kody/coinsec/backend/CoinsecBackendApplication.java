package com.kody.coinsec.backend;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * CoinsecBackendApplication 启动类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.kody.coinsec.backend.module.**.mapper")
public class CoinsecBackendApplication {

    static void main(String[] args) {
        SpringApplication.run(CoinsecBackendApplication.class, args);
    }

}
