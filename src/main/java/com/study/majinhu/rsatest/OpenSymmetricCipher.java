package com.study.majinhu.rsatest;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * 对称加密、解密
 *
 * @author Huang
 *
 */
public class OpenSymmetricCipher implements OpenCipher {

	private String source;
	private String password;
	private String keyAlgorithm;
	private String cipherAlgorithm;
	private Charset charset;
	private boolean custom;

	private Key key;

	/**
	 * 加密、解密使用
	 *
	 * @param source
	 *            待加密、解密数据
	 * @param password
	 *            密码
	 * @param keyAlgorithm
	 *            密钥算法
	 * @param cipherAlgorithm
	 *            加密、解密算法,工作模式,填充方式
	 * @param charset
	 *            字符集
	 * @param custom
	 *            自定义密码,密码不是通过秘钥算法生成true,否则false
	 * @throws Exception
	 */
	public OpenSymmetricCipher(String source, String password, String keyAlgorithm, String cipherAlgorithm,
			Charset charset, boolean custom) throws Exception {
		super();
		this.source = source;
		this.password = password;
		this.keyAlgorithm = keyAlgorithm;
		this.cipherAlgorithm = cipherAlgorithm;
		this.charset = charset;
		this.custom = custom;
		key = toKey(this.password);
	}

	/**
	 * @param keyAlgorithm
	 *            密钥算法
	 */
	public OpenSymmetricCipher(String keyAlgorithm) {
		super();
		this.keyAlgorithm = keyAlgorithm;
	}

	@Override
	public String encrypt() throws Exception {
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(source.getBytes(charset)));
	}

	@Override
	public String decrypt() throws Exception {
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(source)), charset);
	}

	/**
	 * 生成密码
	 *
	 * @return String 密钥
	 * @throws Exception
	 */
	public String initKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
		if (DES.equalsIgnoreCase(keyAlgorithm)) {
			keyGenerator.init(56);
		}
		if (AES.equalsIgnoreCase(keyAlgorithm)) {
			keyGenerator.init(128);
		}
		SecretKey secretKey = keyGenerator.generateKey();
		return Base64.encodeBase64String(secretKey.getEncoded());
	}

	/**
	 * 密钥转化
	 *
	 * @param key
	 *            密钥字符串
	 * @return
	 * @throws Exception
	 */
	private Key toKey(String key) throws Exception {
		byte[] k = null;
		if (!custom) {
			k = Base64.decodeBase64(key);
		} else {
			k = key.getBytes();
		}
		if (DES.equalsIgnoreCase(keyAlgorithm)) {
			DESKeySpec desKeySpec = new DESKeySpec(k);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
			return keyFactory.generateSecret(desKeySpec);
		}
		if (AES.equalsIgnoreCase(keyAlgorithm)) {
			return new SecretKeySpec(k, keyAlgorithm);
		}
		return null;
	}

}
