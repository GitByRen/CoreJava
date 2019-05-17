package com.important.java10;

import java.util.ArrayList;

import org.junit.Test;

public class VarTest {

	/**
	 * 局部变量类型推断：
	 * 它不能使用于方法形式参数，构造函数形式参数，方法返回类型，字段，catch形式参数或任何其他类型的变量声明。
	 */
	@Test
	public void test1() {
		// 根据ABC推断为 字符串类型
		var str = "ABC";
		// 根据10L推断long 类型
		var l = 10L;
		// 根据 true推断 boolean 类型
		var flag = true;
		// 这里会推断boolean类型。0表示false 非0表示true
		var flag1 = 1;
		// 推断 ArrayList<String>
		var list = new ArrayList<String>();
		// 推断 Stream<String>
		var stream = list.stream();
	}
	
}
