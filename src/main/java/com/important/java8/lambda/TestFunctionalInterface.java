package com.important.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * Java8内置的四大核心函数式接口
 * 
 * Consumer<T> : 消费型接口(传入参数，不返回) void accept(T t);
 * 
 * Supplier<T> : 供给型接口(不传入参数，返回) T get();
 * 
 * Function<T, R> : 函数型接口(传入一个参数，返回一个类型) R apply(T t);
 * 
 * Predicate<T> : 断言型接口(用于判断操作) boolean test(T t);
 */
public class TestFunctionalInterface {

	// Consumer<T>
	@Test
	public void test1() {
		happy(1000, (x) -> System.out.println("消费" + x + "圆"));
	}

	// 消费
	public void happy(double money, Consumer<Double> con) {
		con.accept(money);
	}

	// Supplier<T>
	@Test
	public void test2() {
		List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
		System.out.println(numList);
	}

	// 产生指定个数的整数，并放入集合中
	public List<Integer> getNumList(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < num; i++) {
			Integer n = sup.get();
			list.add(n);
		}

		return list;
	}

	// Function<T, R>
	@Test
	public void test3() {
		String strHandler = strHandler("\t\t 东哥帅  ", (str) -> str.trim());
		System.out.println(strHandler);
	}

	// 用于处理字符串
	public String strHandler(String str, Function<String, String> fun) {
		return fun.apply(str);
	}

	// Predicate<T>
	@Test
	public void test4() {
		List<String> list = Arrays.asList("Hello", "AABBC", "哈哈");
		List<String> strList = filterStr(list, (x) -> x.length() > 3);
		System.out.println(strList);
	}

	// 将满足条件的字符串添加到集合中
	public List<String> filterStr(List<String> list, Predicate<String> pre) {
		List<String> lists = new ArrayList<>();
		for (String str : list) {
			if (pre.test(str)) {
				lists.add(str);
			}
		}
		return lists;
	}

}
