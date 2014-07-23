package com.tools.base64;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Encoder;


public class Base64Test {
	public static void main(String[] args) {
		String str = "你们好~！@#￥%…asdfafwerwerwrawffafgggsdhsgdgdgdg…&*)({}[].,;'pufdgdaasdfsdfsdfsdfsfsfsfsdfsfsdfsfs\n                          你好！";
		char[] encodedCharArray = null;
		//将字符串转换为base64字符数组
		try {
			// 参数为true时表示编码后76个字符换行
			encodedCharArray = Base64.encodeToChar(str.getBytes("UTF-8"), true);
			System.out.println(encodedCharArray);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------------------------------");
		//从charArray转化为utf-8的字符串
		byte[] bytes = Base64.decode(encodedCharArray);
		try {
			System.out.println(new String(bytes, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------------------------------");
		
		String encode = "三等奖付款尽快的房间里睡觉法律上几分；大家恩对两个美女，恩放大看法律";
		String str1 = "";
		try {
			str1 = Base64.encodeToString(encode.getBytes("UTF-8"), true);
			System.out.println(str1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------------------------------");
		
		//Java自带Base64编码,编码后76个字符自动换行,最后一行不足76个字符，也自动换行
		BASE64Encoder be = new BASE64Encoder();
		try {
			String str2 = be.encodeBuffer(encode.getBytes("UTF-8"));
			System.out.println(str2);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
