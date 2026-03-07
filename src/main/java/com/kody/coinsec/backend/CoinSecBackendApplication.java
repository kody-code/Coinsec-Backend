package com.kody.coinsec.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author Kody
 * @since 2026-03-07
 */
@Slf4j
@SpringBootApplication
public class CoinSecBackendApplication {

    static void main(String[] args) {
        SpringApplication.run(CoinSecBackendApplication.class, args);
        log.info("启动成功.....");
    }

}
