package com.study.majinhu.rsatest;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class OpenKey {

	private String keyAlgorithm;
	private Charset charset;

	/**
	 * @param keyAlgorithm
	 *            密钥算法
	 * @param charset
	 *            字符集
	 */
	public OpenKey(String keyAlgorithm, Charset charset) {
		super();
		this.keyAlgorithm = keyAlgorithm;
		this.charset = charset;
	}

	/**
	 * 私钥转换
	 *
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public PrivateKey privateKey(String privateKey) throws Exception {
		byte[] bt = Base64.decodeBase64(privateKey.getBytes(charset));
		return KeyFactory.getInstance(keyAlgorithm).generatePrivate(new PKCS8EncodedKeySpec(bt));
	}

	/**
	 * 公钥转换
	 *
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public PublicKey publicKey(String publicKey) throws Exception {
		byte[] bt = Base64.decodeBase64(publicKey.getBytes(charset));
		return KeyFactory.getInstance(keyAlgorithm).generatePublic(new X509EncodedKeySpec(bt));
	}

}
