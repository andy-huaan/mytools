package com.tools.base64;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Encoder;


public class Base64Test {
	public static void main(String[] args) {
		String str = "���Ǻ�~��@#��%��asdfafwerwerwrawffafgggsdhsgdgdgdg��&*)({}[].,;'pufdgdaasdfsdfsdfsdfsfsfsfsdfsfsdfsfs\n                          ��ã�";
		char[] encodedCharArray = null;
		//���ַ���ת��Ϊbase64�ַ�����
		try {
			// ����Ϊtrueʱ��ʾ�����76���ַ�����
			encodedCharArray = Base64.encodeToChar(str.getBytes("UTF-8"), true);
			System.out.println(encodedCharArray);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------------------------------");
		//��charArrayת��Ϊutf-8���ַ���
		byte[] bytes = Base64.decode(encodedCharArray);
		try {
			System.out.println(new String(bytes, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------------------------------");
		
		String encode = "���Ƚ������ķ�����˯�������ϼ��֣���Ҷ���������Ů�����Ŵ󿴷���";
		String str1 = "";
		try {
			str1 = Base64.encodeToString(encode.getBytes("UTF-8"), true);
			System.out.println(str1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------------------------------------------");
		
		//Java�Դ�Base64����,�����76���ַ��Զ�����,���һ�в���76���ַ���Ҳ�Զ�����
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
