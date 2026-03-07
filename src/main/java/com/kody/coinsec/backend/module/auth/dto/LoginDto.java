package com.kody.coinsec.backend.module.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>
 * 登录参数
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-07
 */
@Data
@Schema(description = "登录参数")
public class LoginDto {

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名", example = "kody")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码", example = "097016@Wzj!")
    private String password;
}
