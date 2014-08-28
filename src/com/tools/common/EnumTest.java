package com.tools.common;

/**
 * 获取枚举类型索引
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
