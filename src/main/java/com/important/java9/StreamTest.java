package com.important.java9;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

	/**
	 * takeWhile：返回从头开始的满足条件的元素
	 */
	@Test
	public void test1() {
		List<Integer> list = Arrays.asList(45, 56, 78, 12, 33);
		list.stream().takeWhile(x -> x < 60).forEach(System.out::println);
	}
	
	/**
	 * dropWhile：去除从头开始的满足条件的元素
	 */
	@Test
	public void test2() {
		List<Integer> list = Arrays.asList(45, 56, 78, 12, 33);
		list.stream().dropWhile(x -> x < 60).forEach(System.out::println);
	}
	
	/**
	 * Stream的ofNullable
	 */
	@Test
	public void test3() {
		Stream<Object> stream = Stream.of("1", 2, null);
		stream.forEach(System.out::println);
		
		// NullPointerException
//		Stream<Object> ofNull = Stream.of(null);
//		ofNull.forEach(System.out::println);
		
		Stream<Object> ofNullable = Stream.ofNullable(null);
		System.out.println(ofNullable.count());
	}
	
	/**
	 * iterator()重载的方法
	 */
	@Test
	public void test4() {
		// jdk1.8
		Stream.iterate(0, x -> x + 2).limit(10).forEach(System.out::println);

		// jdk1.9
		Stream.iterate(0, x -> x < 10, x -> x + 2).forEach(System.out::println);

	}
	
}
