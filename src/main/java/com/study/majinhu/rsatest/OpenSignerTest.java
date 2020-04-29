package com.study.majinhu.rsatest;

import org.apache.commons.codec.Charsets;

public class OpenSignerTest {

	public static void main(String[] args) throws Exception {
		String signSource = "hello";
		OpenSigner openSigner = OpenSigner.getInstance("DSA", null);
		openSigner.initKey();
		String publicKey = openSigner.getPublic();
		String privateKey = openSigner.getPrivate();
		openSigner = OpenSigner.getInstance("DSA", "SHA256withDSA", publicKey, privateKey, Charsets.UTF_8);
		String sign = openSigner.sign(signSource);
		System.out.println("签值：\n" + sign);
		System.out.println("验签结果: \n" + openSigner.verify(sign, signSource));
	}

}
