package com.kody.coinsec.backend.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.kody.coinsec.backend.common.response.ApiResponse;
import com.kody.coinsec.backend.common.response.ErrorDetail;
import com.kody.coinsec.backend.common.response.ResponseCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 全局异常处理器 统一处理各种异常并返回标准格式的错误响应
 * </p>
 *
 * @author Kody
 * @version 1.0
 * @since 2026-03-07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	// ================== 1. 业务异常 (新增) ==================

	/**
	 * <p>
	 * 处理业务异常
	 * </p>
	 *
	 * @param e 业务异常对象
	 *
	 * @return 错误响应，包含错误码和错误信息
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<Void> handleBusinessException(BusinessException e) {
		// 1. 记录带上下文的日志（便于排查）
		log.warn("业务异常 [code={}]: {} | context={}",
				e.getCode(), e.getMessage(), e.getContext());
		// 2. 将 context 转为结构化错误详情（供前端使用）
		List<ErrorDetail> details = new ArrayList<>();
		if (e.getContext() != null) {
			// 情况A：context 包含字段级错误（如验证失败）
			if (e.getContext().containsKey("fieldErrors")) {
				List<Map<String, String>> errors = (List<Map<String, String>>) e.getContext().get("fieldErrors");
				errors.forEach(err -> details.add(
						ErrorDetail.ofField(err.get("field"), err.get("message"))
				));
			}
			// 情况B：通用 context 转为全局错误（带元数据）
			else {
				details.add(ErrorDetail.ofGlobal(
						e.getMessage(),
						e.getContext()
				));
			}
		}

		// 3. 返回标准化响应（含结构化错误）
		return details.isEmpty()
				? ApiResponse.error(e.getCode(), e.getMessage())
				: createErrorResponse(e.getCode(), e.getMessage(), details);
	}

	/**
	 * 创建带错误详情的错误响应
	 *
	 * @param code    状态码
	 * @param message 错误消息
	 * @param details 错误详情列表
	 *
	 * @return 错误响应
	 */
	private ApiResponse<Void> createErrorResponse(Integer code, String message, List<ErrorDetail> details) {
		ApiResponse<Void> response = new ApiResponse<>(code, message, null);
		response.setErrors(details);
		return response;
	}

	// ================== 2. 登陆验证 ==================

	/**
	 * <p>
	 * 登陆验证异常
	 * </p>
	 *
	 * @param e 登陆验证异常对象
	 *
	 * @return 错误响应，包含错误信息
	 */
	@ExceptionHandler(NotLoginException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiResponse<Void> handleNotLoginException(NotLoginException e) {
		log.warn("认证异常: 用户未登录或 Token 无效, 提示: {}, 异常类型: {}", e.getMessage(), e.getType());
		// 根据不同的异常类型，返回不同的错误提示，便于前端做不同处理
		String message = switch (e.getType()) {
			case NotLoginException.NOT_TOKEN -> "未提供 Token，请登录";
			case NotLoginException.INVALID_TOKEN -> "Token 无效，请重新登录";
			case NotLoginException.TOKEN_TIMEOUT -> "登录已过期，请重新登录";
			case NotLoginException.BE_REPLACED -> "账号已在其他地方登录，您被迫下线";
			case NotLoginException.KICK_OUT -> "您已被踢下线";
			default -> "登录校验失败: " + e.getMessage();
		};
		return ApiResponse.error(ResponseCode.UNAUTHORIZED, message);
	}

	// ================== 3. 参数校验异常 ==================

	/**
	 * <p>
	 * 处理方法参数校验异常 捕获并处理使用@Valid 注解引起的参数校验失败异常
	 * </p>
	 *
	 * @param e 方法参数校验异常对象，包含校验失败的详细信息
	 *
	 * @return 包含错误详情列表的 API 响应对象，返回所有校验失败的字段及错误信息
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<List<ErrorDetail>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.warn("参数校验异常 (@Valid): {}", e.getMessage());
		return processValidationErrors(e.getBindingResult());
	}

	/**
	 * <p>
	 * 参数校验异常(@Validated)
	 * </p>
	 *
	 * @param e 异常对象
	 *
	 * @return 错误响应
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<List<ErrorDetail>> handleConstraintViolationException(ConstraintViolationException e) {
		log.warn("参数校验异常(@Validated): {}", e.getMessage());
		List<ErrorDetail> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			ErrorDetail error = new ErrorDetail();
			error.setField(violation.getPropertyPath().toString());
			error.setMessage(violation.getMessage());
			errors.add(error);
		}
		return ApiResponse.error(ResponseCode.VALIDATION_ERROR, errors);
	}

	/**
	 * <p>
	 * 处理参数绑定异常 捕获并处理使用@RequestBody 注解引起的参数绑定失败异常
	 * </p>
	 *
	 * @param e 参数绑定异常对象，包含绑定失败的详细信息
	 *
	 * @return 错误响应，包含错误详情列表
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<List<ErrorDetail>> handleBindException(BindException e) {
		log.warn("参数绑定异常: {}", e.getMessage());
		return processValidationErrors(e.getBindingResult());
	}

	// ================== 4. Web 请求异常 (通用) ==================

	/**
	 * <p>
	 * 处理客户端请求异常
	 * </p>
	 *
	 * @param e 客户端请求异常对象
	 *
	 * @return 错误响应
	 */
	@ExceptionHandler({
			MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class,
			HttpMessageNotReadableException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<Void> handleClientExceptions(Exception e) {
		log.warn("客户端请求异常: {}", e.getMessage());
		if (e instanceof HttpMessageNotReadableException) {
			return ApiResponse.error(ResponseCode.MESSAGE_NOT_READABLE);
		} else if (e instanceof MethodArgumentTypeMismatchException) {
			return ApiResponse.error(ResponseCode.PARAMETER_TYPE_MISMATCH);
		}
		return ApiResponse.error(ResponseCode.BAD_REQUEST);
	}

	/**
	 * <p>
	 * 处理 HTTP 请求方法不支持异常 捕获并处理使用@RequestMapping 注解引起的 HTTP 请求方法不支持异常
	 * </p>
	 *
	 * @return 错误响应，包含错误信息
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ApiResponse<Void> handleMethodNotAllowed() {
		return ApiResponse.error(ResponseCode.METHOD_NOT_ALLOWED);
	}

	/**
	 * <p>
	 * 处理 HTTP 媒体类型不支持异常
	 * </p>
	 *
	 * @return 错误响应
	 */
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ApiResponse<Void> handleMediaTypeNotSupported() {
		return ApiResponse.error(ResponseCode.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * <p>
	 * 处理 HTTP 请求路径不存在异常 捕获并处理使用@RequestMapping 注解引起的 HTTP 请求路径不存在异常
	 * </p>
	 *
	 * @return 错误响应，包含错误信息
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiResponse<Void> handleNotFound() {
		return ApiResponse.error(ResponseCode.NOT_FOUND);
	}

	// ================== 5. JPA / 数据库 特有异常 (核心修改点) ==================

	/**
	 * 处理 JPA 事务异常 (如：Entity 未找到、级联保存错误、类型转换错误等) 这是你刚才遇到 "expression is of type character varying" 错误时会捕获的异常
	 */
	@ExceptionHandler(TransactionSystemException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 通常这类是数据格式或逻辑错误，非服务器崩溃
	public ApiResponse<Void> handleJpaTransactionException(TransactionSystemException e) {
		log.error("JPA 事务异常: 可能是数据格式错误、校验失败或约束冲突", e);
		return ApiResponse.error(ResponseCode.DATABASE_CONSTRAINT_VIOLATION);
	}

	/**
	 * 处理数据完整性违规 (如：唯一键冲突、非空字段为空、外键约束) 这是 JPA 中最常见的数据库错误类型
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT) // 409 Conflict 适合处理唯一键冲突
	public ApiResponse<Void> handleDataIntegrityViolation(DataIntegrityViolationException e) {
		log.warn("数据完整性违规: 可能是唯一索引冲突或外键错误: {}", e.getMessage());
		return ApiResponse.error(ResponseCode.DATA_INTEGRITY_VIOLATION);
	}

	/**
	 * 处理所有其他数据库访问异常 (兜底)
	 */
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse<Void> handleDataAccessException(DataAccessException e) {
		// 注意：这里移除了 MyBatis 的异常处理
		log.error("数据库访问异常 (JPA): ", e);
		return ApiResponse.error(ResponseCode.DATABASE_ERROR);
	}

	// ================== 6. 通用/未知异常 ==================
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse<Void> handleGeneralException(Exception e) {
		log.error("未知系统异常: ", e);
		return ApiResponse.error(ResponseCode.INTERNAL_ERROR);
	}

	// ================== 工具方法 ==================

	/**
	 * 处理参数校验错误
	 *
	 * @param bindingResult 参数校验结果
	 *
	 * @return 错误响应
	 */
	private ApiResponse<List<ErrorDetail>> processValidationErrors(BindingResult bindingResult) {
		List<ErrorDetail> errors = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			ErrorDetail error = new ErrorDetail();
			error.setField(fieldError.getField());
			error.setMessage(fieldError.getDefaultMessage());
			error.setRejectedValue(fieldError.getRejectedValue());
			errors.add(error);
		}
		return ApiResponse.error(ResponseCode.VALIDATION_ERROR, errors);
	}
}
