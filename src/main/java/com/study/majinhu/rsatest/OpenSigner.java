package com.study.majinhu.rsatest;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

/**
 * 加签、验签
 *
 * @author Huang
 *
 */
public class OpenSigner {

	/**
	 * 默认密钥算法
	 */
	private static final String KEY_ALGORITHM_DEFAULT = "RSA";
	/**
	 * 默认签名算法
	 */
	private static final String SIGNATURE_ALGORITHM_DEFAULT = "SHA256withRSA";

	private static final String PUBLIC_KEY = "publicKey";
	private static final String PRIVATE_KEY = "privateKey";

	/**
	 * 默认密钥长度
	 */
	private static final int KEY_SIZE = 1024;

	private String keyAlgorithm;
	private String signatureAlgorithm;
	private String publicKey;
	private String privateKey;
	private Charset charset;
	private Integer keySize;

	private Map<String, Key> map = new HashMap<String, Key>();

	/**
	 * 生成公钥、私钥使用
	 *
	 * @param keyAlgorithm
	 *            密钥算法 默认 RSA
	 * @param keySize
	 *            秘钥长度 默认1024,null表示使用默认的秘钥长度.
	 *            <li>RSA密钥算法的秘钥长度范围512~65536,必须为64的倍数;</li>
	 *            <li>DSA密钥算法的秘钥长度范围512~1024,必须为64的倍数;</li>
	 */
	public static OpenSigner getInstance(String keyAlgorithm, Integer keySize) {
		return new OpenSigner(keyAlgorithm, null, null, null, null, keySize);
	}

	/**
	 * 加签、验签使用
	 *
	 * @param keyAlgorithm
	 *            密钥算法 默认 RSA,空字符串表示使用默认算法
	 * @param signatureAlgorithm
	 *            签名算法 默认 SHA256withRSA,空字符串表示使用默认算法
	 * @param publicKey
	 *            公钥
	 * @param privateKey
	 *            私钥
	 * @param charset
	 *            字符集
	 */
	public static OpenSigner getInstance(String keyAlgorithm, String signatureAlgorithm, String publicKey, String privateKey,
			Charset charset) {
		return new OpenSigner(keyAlgorithm, signatureAlgorithm, publicKey, privateKey, charset, null);
	}

	private OpenSigner(String keyAlgorithm, String signatureAlgorithm, String publicKey, String privateKey,
			Charset charset, Integer keySize) {
		super();
		this.keyAlgorithm = StringUtils.empty(keyAlgorithm) ? KEY_ALGORITHM_DEFAULT : keyAlgorithm;
		this.signatureAlgorithm = StringUtils.empty(signatureAlgorithm) ? SIGNATURE_ALGORITHM_DEFAULT
				: signatureAlgorithm;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.charset = charset;
		this.keySize = keySize == null ? KEY_SIZE : keySize;
	}

	/**
	 * 加签
	 *
	 * @param source
	 *            待加签数据
	 * @param signName
	 *            签名域
	 * @return Map<String, String> 包含签值的加签前数据
	 * @throws Exception
	 */
	public Map<String, String> sign(Map<String, String> source, String signName) throws Exception {
		String sortSource = StringUtils.sortMap(source);
		source.put(signName, sign(sortSource));
		return source;
	}

	/**
	 * 加签
	 *
	 * @param source
	 *            待加签数据
	 * @return String 签值
	 * @throws Exception
	 */
	public String sign(String source) throws Exception {
		return new String(Base64.encodeBase64(sign(source.getBytes(charset))), charset);
	}

	/**
	 * 加签
	 *
	 * @param source
	 *            待加签数据
	 * @return byte[] 签值
	 * @throws Exception
	 */
	public byte[] sign(byte[] source) throws Exception {
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initSign(new OpenKey(keyAlgorithm, charset).privateKey(privateKey));
		signature.update(source);
		return signature.sign();
	}

	/**
	 * 验签
	 *
	 * @param source
	 *            包含签值的待验签数据
	 * @param signName
	 *            签名域
	 * @return boolean 验签成功返回true 失败返回false
	 * @throws Exception
	 */
	public boolean verify(Map<String, String> source, String signName) throws Exception {
		String sign = source.remove(signName);
		return verify(sign, source);
	}

	/**
	 * 验签
	 *
	 * @param sign
	 *            签值
	 * @param source
	 *            待验签数据
	 * @return boolean 验签成功返回true 失败返回false
	 * @throws Exception
	 */
	public boolean verify(String sign, Map<String, String> source) throws Exception {
		String sortSource = StringUtils.sortMap(source);
		return verify(sign, sortSource);
	}

	/**
	 * 验签
	 *
	 * @param sign
	 *            签值
	 * @param source
	 *            待验签数据
	 * @return boolean 验签成功返回true 失败返回false
	 * @throws Exception
	 */
	public boolean verify(String sign, String source) throws Exception {
		return verify(Base64.decodeBase64(sign.getBytes(charset)), source.getBytes(charset));
	}

	/**
	 * 验签
	 *
	 * @param sign
	 *            签值
	 * @param source
	 *            待验签数据
	 * @return boolean 验签成功返回true 失败返回false
	 * @throws Exception
	 */
	public boolean verify(byte[] sign, byte[] source) throws Exception {
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initVerify(new OpenKey(keyAlgorithm, charset).publicKey(publicKey));
		signature.update(source);
		return signature.verify(sign);
	}

	/**
	 * 生成密钥
	 *
	 * @throws Exception
	 */
	public void initKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyAlgorithm);
		keyPairGenerator.initialize(keySize, new SecureRandom());
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		PublicKey publicKey = (PublicKey) keyPair.getPublic();
		PrivateKey privateKey = (PrivateKey) keyPair.getPrivate();
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
	}

	/**
	 * 获取公钥
	 *
	 * @return
	 */
	public String getPublic() {
		return Base64.encodeBase64String(map.get(PUBLIC_KEY).getEncoded());
	}

	/**
	 * 获取私钥
	 *
	 * @return
	 */
	public String getPrivate() {
		return Base64.encodeBase64String(map.get(PRIVATE_KEY).getEncoded());
	}

}
