package com.gym.util;

import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;

public class EmojiFilter {
	public static String filter(String str) throws PatternSyntaxException {
		// 只允许字母和数字和中文//[\\pP‘’“”
		String regEx = "^[A-Za-z\\d\\u4E00-\\u9FA5\\p{P}‘’“” ]+$";
		StringBuilder sb = new StringBuilder(StringUtils.trim(str));

		for (int len = str.length(), i = len - 1; i >= 0; --i) {
			if (!Pattern.matches(regEx, String.valueOf(str.charAt(i)))) {
				sb.deleteCharAt(i);
			}
		}

		return sb.toString();
	}


	public static void main(String[] args) {
		String str = " 中文 测试生僻讠？？？???！！！!!!...。。。";
		System.out.println(filter(str));
		
		System.out.println(new Date().getTime());
		System.out.println(new Date(1471947500983L));
		
		
		String test = "test   https://www.youku.com/test.html";
		System.out.println(test.replaceAll("https://", "http://"));
	}
}