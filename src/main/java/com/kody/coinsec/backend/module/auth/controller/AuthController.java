package com.kody.coinsec.backend.module.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.common.response.ResponseCode;
import com.kody.coinsec.backend.module.auth.dto.LoginDto;
import com.kody.coinsec.backend.module.auth.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 认证接口
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "认证接口", description = "用户注册、登录、信息管理等相关接口")
public class AuthController {

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    /**
     * <p>
     * 登录接口
     * </p>
     *
     * @param loginDto 登录参数
     * @return 登录结果
     */
    @PostMapping("/login")
    @Operation(summary = "登录接口", description = "用户账号登录获取访问凭证")
    public ApiResponse<LoginVo> login(@Valid @RequestBody LoginDto loginDto) {
        log.debug("用户登录开始, 参数: {}", loginDto);
        if (username.equals(loginDto.getUsername()) && password.equals(loginDto.getPassword())) {
            log.info("用户登录成功");
            StpUtil.login(1);
            LoginVo loginVo = new LoginVo();
            loginVo.setUsername(loginDto.getUsername())
                    .setTokenName(StpUtil.getTokenName())
                    .setTokenValue(StpUtil.getTokenValue());
            return ApiResponse.success(loginVo);
        } else {
            return ApiResponse.error(ResponseCode.LOGIN_FAILED);
        }
    }
}
