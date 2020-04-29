package com.study.majinhu.rsatest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StringUtils {

	/**
	 * 空字符串校验
	 *
	 * @param source
	 *            待校验字符串
	 * @return boolean 字符串为空返回true 字符串不为空返回false
	 */
	public static boolean empty(String source) {
		if (source == null || "".equals(source.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * Map排序
	 *
	 * @param source
	 *            待排序
	 * @return String Map排序后的字符串 key1=value1&key2=value2
	 */
	public static String sortMap(Map<String, String> source) {
		StringBuffer b = new StringBuffer();
		List<String> keys = new ArrayList<String>(source.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			b.append(keys.get(i)).append("=").append(source.get(keys.get(i))).append("&");
		}
		return b.substring(0, b.length() - 1);
	}

	/**
	 * 16位随机数
	 *
	 * @return String
	 */
	public static String randomKey() {
		return String.format("%016x", new Random().nextLong());
	}

}
