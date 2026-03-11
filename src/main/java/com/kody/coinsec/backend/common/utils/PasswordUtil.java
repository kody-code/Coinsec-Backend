package com.kody.coinsec.backend.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 密码工具类 使用 SHA-256 哈希算法进行密码加密
 * </p>
 *
 * @author kody
 * @version 1.0
 * @since 2026-03-10
 */
public class PasswordUtil {

	/**
	 * 私有构造函数，防止实例化
	 */
	private PasswordUtil() {
		throw new UnsupportedOperationException("工具类不能实例化");
	}

	/**
	 * 对密码进行 SHA-256 加密
	 *
	 * @param password 原始密码
	 *
	 * @return 加密后的密码（64 位十六进制字符串）
	 */
	public static String encrypt(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 算法不可用", e);
		}
	}

	/**
	 * 验证密码是否匹配
	 *
	 * @param rawPassword       原始密码
	 * @param encryptedPassword 加密后的密码
	 *
	 * @return 是否匹配
	 */
	public static boolean verify(String rawPassword, String encryptedPassword) {
		String hashedInput = encrypt(rawPassword);
		return hashedInput.equals(encryptedPassword);
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 *
	 * @param bytes 字节数组
	 *
	 * @return 十六进制字符串
	 */
	private static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
