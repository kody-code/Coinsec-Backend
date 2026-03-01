package com.kody.coinsec.backend.controller.test;

import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.common.response.ResponseCode;
import com.kody.coinsec.backend.module.user.controller.UserController;
import org.junit.jupiter.api.Test;

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
public class ControllerTest {

    @Test
    void testUserControllerResponse() {
        // 直接实例化Controller进行测试，不依赖Spring上下文
        UserController controller = new UserController();

        // 测试Controller方法返回预期的"待实现"响应
        ApiResponse<Void> response = controller.register(new UserController.RegisterRequest());

        assertThat(response.getCode()).isEqualTo(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        assertThat(response.getMessage()).isEqualTo("功能待实现");
        assertThat(response.isError()).isTrue();
    }
}