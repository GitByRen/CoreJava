package com.important.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.important.java8.lambda.Employee;

/**
 * 什么是Stream：是数据渠道，用于操作数据源(集合，数组等)所生成的元素序列。
 * 
 * 注意：
 * ①Stream自己不会存储元素
 * ②Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream
 * ③Stream操作是延迟执行的，这意味着他们会等到需要结果的时候才执行
 * 
 * 
 * 一、Stream的三个操作步骤：
 * 1.创建stream
 * 2.中间操作
 * 3.终止操作
 *
 */
public class StreamApi {

	// 创建stream
	@Test
	public void test1() {
		// 1.可以通过Collection系列集合提供的stream()或parallelStream()
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();

		// 2.通过Arrays中的静态方法stream()获取数组流
		Employee[] emps = new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(emps);

		// 3.通过Stream类中的静态方法of()
		Stream<String> of = Stream.of("aa", "bb", "cc");

		// 4.无限流
		// 迭代
		Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
		iterate.limit(5).forEach(System.out::println);

		// 生成
		Stream.generate(() -> Math.random()).limit(6).forEach(System.out::println);
	}

	
	// 中间操作
	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66),
			new Employee(101, "张三", 18, 9999.99),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55)
	);
	/*
	  	筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */
	//内部迭代：迭代操作 Stream API 内部完成
	@Test
	public void test2() {
		Stream<Employee> s = emps.stream()
			.filter((e) -> e.getAge() > 35);
		//只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
		s.forEach(System.out::println);
	}
	
	// 外部迭代
	@Test
	public void test3() {
		Iterator<Employee> iterator = emps.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	// 不需要迭代所有，只需要找到满足条件的就不在继续迭代
	@Test
	public void test4() {
		emps.stream()
			.filter((e) -> {
				System.out.println("短路");
				return e.getSalary() > 5000;
			})
			.limit(2)
			.forEach(System.out::println);
	}
	
	// 跳过
	@Test
	public void test5() {
		emps.stream()
			.filter((e) -> e.getSalary() > 5000)
			.skip(2)
			.forEach(System.out::println);
	}
	
	@Test
	public void test6() {
		emps.stream()
			.filter((e) -> e.getSalary() > 5000)
			.distinct()
			.forEach(System.out::println);
	}
}
