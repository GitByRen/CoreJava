package com.important.java8.lambda;

import org.junit.Test;

/**
 * 1.什么是函数式接口？
 * 2.引入需求，定义一个函数式接口，处理字符串；抛出需求，定义一个函数是接口，处理两个Long型数据
 * 3.引入java8中内置的函数式接口：
 * 4.方法引用
 */
public class ExerciseLambada {

	// 需求：用于处理字符串
	public String strHandler(String str, MyFun my) {
		return my.getValue(str);
	}

	@Test
	public void test1() {
		strHandler("\t\t\t 哈哈  ", new MyFun() {
			@Override
			public String getValue(String i) {
				return i.trim();
			}
		});

		// trim
		System.out.println(strHandler("\t\t\t  嘎嘎 ", (x) -> x.trim()));

		// 大写
		System.out.println(strHandler("abv", (x) -> x.toUpperCase()));
	}

	// 需求：对于两个Long型数据进行处理
	public void operate(Long l1, Long l2, MyFun2<Long, Long> mf) {
		System.out.println(mf.getValue(l1, l2));
	}
	
	@Test
	public void test2() {
		operate(100L, 200L, (x, y) -> x + y);
		operate(100L, 200L, (x, y) -> x - y);
	}

}
