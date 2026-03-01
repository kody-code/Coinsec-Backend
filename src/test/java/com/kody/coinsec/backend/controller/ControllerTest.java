package com.kody.coinsec.backend.controller;

import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.common.response.ResponseCode;
import com.kody.coinsec.backend.module.user.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>
 * Controller测试类
 * 验证Controller组件是否正确加载
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@SpringBootTest(
        classes = ControllerTest.TestConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class ControllerTest {

    @Test
    void testUserControllerLoads(ApplicationContext context) {
        // 验证UserController bean是否存在
        assertThat(context.getBean(UserController.class)).isNotNull();

        // 测试Controller方法返回预期的"待实现"响应
        UserController controller = context.getBean(UserController.class);
        ApiResponse<Void> response = controller.register(new UserController.RegisterRequest());

        assertThat(response.getCode()).isEqualTo(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        assertThat(response.getMessage()).isEqualTo("功能待实现");
        assertThat(response.isError()).isTrue();
    }

    /**
     * 测试配置类 - 只扫描Controller组件
     */
    @ComponentScan(
            basePackages = "com.kody.coinsec.backend.module",
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    classes = org.springframework.web.bind.annotation.RestController.class
            )
    )
    static class TestConfig {
        // 空配置类，仅用于组件扫描
    }
}