package com.kody.coinsec.backend.common.exception;

import lombok.Getter;

import java.util.Map;

/**
 * <p>
 * 业务异常
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-08
 */
@Getter
public class BusinessException extends RuntimeException {

	private final int code;
	private final Map<String, Object> context;

	public BusinessException(int code, String message, Map<String, Object> context) {
		super(message);
		this.code = code;
		this.context = context;
	}
}
