package com.tools.common;

/**
 * ��ȡö����������
 * @author zhaohuaan
 *
 */
public class EnumTest {
	public enum Color {
		RED, GREEN, BLANK, YELLO;
	}
	
	public static void main(String[] args) {
		int a = Color.YELLO.ordinal();
		System.out.println(a);
	}
}
