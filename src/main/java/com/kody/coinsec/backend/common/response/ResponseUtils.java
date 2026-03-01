package com.kody.coinsec.backend.common.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 响应工具类
 * 提供便捷的响应写入方法
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Slf4j
public class ResponseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 写入JSON响应
     * 
     * @param response HttpServletResponse对象
     * @param apiResponse ApiResponse对象
     * @param <T> 数据类型
     */
    public static <T> void writeJsonResponse(HttpServletResponse response, ApiResponse<T> apiResponse) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setStatus(HttpStatus.OK.value());
            
            String jsonResponse = objectMapper.writeValueAsString(apiResponse);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error("写入响应失败", e);
        }
    }

    /**
     * 写入成功响应
     * 
     * @param response HttpServletResponse对象
     * @param data 响应数据
     * @param <T> 数据类型
     */
    public static <T> void writeSuccessResponse(HttpServletResponse response, T data) {
        ApiResponse<T> apiResponse = ApiResponse.success(data);
        writeJsonResponse(response, apiResponse);
    }

    /**
     * 写入错误响应
     * 
     * @param response HttpServletResponse对象
     * @param responseCode 响应码
     */
    public static void writeErrorResponse(HttpServletResponse response, ResponseCode responseCode) {
        ApiResponse<Void> apiResponse = ApiResponse.error(responseCode);
        writeJsonResponse(response, apiResponse);
    }

    /**
     * 写入带错误详情的错误响应
     * 
     * @param response HttpServletResponse对象
     * @param responseCode 响应码
     * @param errors 错误详情列表
     */
    public static void writeErrorResponse(HttpServletResponse response, ResponseCode responseCode, 
                                        java.util.List<ErrorDetail> errors) {
        ApiResponse<Void> apiResponse = ApiResponse.error(responseCode, errors);
        writeJsonResponse(response, apiResponse);
    }

    /**
     * 设置响应状态码
     * 
     * @param response HttpServletResponse对象
     * @param status HttpStatus状态
     */
    public static void setStatus(HttpServletResponse response, HttpStatus status) {
        response.setStatus(status.value());
    }

    /**
     * 设置响应头
     * 
     * @param response HttpServletResponse对象
     * @param name 头名称
     * @param value 头值
     */
    public static void setHeader(HttpServletResponse response, String name, String value) {
        response.setHeader(name, value);
    }
}