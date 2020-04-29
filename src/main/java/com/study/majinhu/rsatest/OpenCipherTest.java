package com.study.majinhu.rsatest;

import org.apache.commons.codec.Charsets;

public class OpenCipherTest {

	public static void main(String[] args) throws Exception {
		symmetric();
//		asymmetric();
	}

	public static void symmetric() throws Exception {
		String keyAlgorithm = OpenCipher.AES;
		String cipherAlgorithm = "AES/ECB/PKCS5Padding";
		String source = "hello";
		OpenSymmetricCipher symmetricCipher = new OpenSymmetricCipher(keyAlgorithm);
		String password = symmetricCipher.initKey();
//		String password = StringUtils.randomKey();
		System.out.println("密钥: \n" + password);
		symmetricCipher = new OpenSymmetricCipher(source, password, keyAlgorithm, cipherAlgorithm,
				Charsets.UTF_8, false);
		String encrypt = symmetricCipher.encrypt();
		System.out.println("加密后: \n" + encrypt);
		symmetricCipher = new OpenSymmetricCipher(encrypt, password, keyAlgorithm, cipherAlgorithm,
				Charsets.UTF_8, false);
		String decrypt = symmetricCipher.decrypt();
		System.out.println("解密后: \n" + decrypt);
	}

	public static void asymmetric() throws Exception {
		String source = "hello";
		OpenSigner openSigner = OpenSigner.getInstance("DSA", null);
		openSigner.initKey();
		String publicKey = openSigner.getPublic();
		String privateKey = openSigner.getPrivate();

		OpenAsymmetricCipher asymmetricCipher = new OpenAsymmetricCipher(source, "RSA", publicKey, privateKey, Charsets.UTF_8);
		String encrypt = asymmetricCipher.encrypt();
		System.out.println("加密后: \n" + encrypt);
		asymmetricCipher = new OpenAsymmetricCipher(encrypt, "RSA", publicKey, privateKey, Charsets.UTF_8);
		String dencrypt = asymmetricCipher.decrypt();
		System.out.println("解密后: \n" + dencrypt);
	}

}
