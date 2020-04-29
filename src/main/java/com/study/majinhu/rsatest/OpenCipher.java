package com.study.majinhu.rsatest;

public interface OpenCipher {

	public static final String DES = "DES";
	public static final String AES = "AES";

	/**
	 * 加密
	 *
	 * @return String 加密后数据
	 * @throws Exception
	 */
	public String encrypt() throws Exception;

	/**
	 * 解密
	 *
	 * @return String 解密后数据
	 */
	public String decrypt() throws Exception;

}
