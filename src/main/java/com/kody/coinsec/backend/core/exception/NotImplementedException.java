package com.kody.coinsec.backend.core.exception;

import com.kody.coinsec.backend.common.exception.BusinessException;
import com.kody.coinsec.backend.common.response.ResponseCode;

import java.util.Map;

/**
 * <p>
 * 未实现异常
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
public class NotImplementedException extends BusinessException {
	public NotImplementedException(Map<String, Object> context) {
		super(ResponseCode.NOT_IMPLEMENTED.getCode(), ResponseCode.NOT_IMPLEMENTED.getMessage(), context);
	}
}
