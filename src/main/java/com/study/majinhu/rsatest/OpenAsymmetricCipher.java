package com.study.majinhu.rsatest;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.Charset;

/**
 * 非对称加解密
 *
 * @author Huang
 *
 */
public class OpenAsymmetricCipher implements OpenCipher {

	private String source;
	private String keyAlgorithm;
	private String publicKey;
	private String privateKey;
	private Charset charset;

	/**
	 * @param source
	 *            待加密数据
	 * @param keyAlgorithm
	 *            密钥算法
	 * @param publicKey
	 *            公钥
	 * @param privateKey
	 *            私钥
	 * @param charset
	 *            字符集
	 */
	public OpenAsymmetricCipher(String source, String keyAlgorithm, String publicKey, String privateKey,
			Charset charset) {
		super();
		this.source = source;
		this.keyAlgorithm = keyAlgorithm;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.charset = charset;
	}

	@Override
	public String encrypt() throws Exception {
		Cipher cipher = Cipher.getInstance(keyAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, new OpenKey(keyAlgorithm, charset).publicKey(publicKey));
		return Base64.encodeBase64String(cipher.doFinal(source.getBytes(charset)));
	}

	@Override
	public String decrypt() throws Exception {
		Cipher cipher = Cipher.getInstance(keyAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, new OpenKey(keyAlgorithm, charset).privateKey(privateKey));
		return new String(cipher.doFinal(Base64.decodeBase64(source)), charset);
	}

}
