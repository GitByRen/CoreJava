package com.important.java8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 一、方法引用：若Lambda体中的内容已经有方法实现了，我们可以使用"方法引用" 
 * (可以理解为方法引用是Lambda表达式的另外一种表现形式)
 * 
 * 主要有三种语法格式： 
 * 	对象::实例方法名 
 * 	类::静态方法名 
 * 	类::实例方法名
 * 
 * 注意：
 * ①Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值保持一致！
 * ②若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 * 
 * 二、构造器引用
 * ClassName::new
 * 
 * 注意：需要调用的构造器的参数列表要与函数式接口中的抽象方法的参数列表要一致！
 * 
 * 三：数组引用
 * Type::new
 */
public class TestMethodReference {
	
	// 数组引用
	@Test
	public void test6() {
		Function<Integer, String[]> fun = (x) -> new String[x];
		String[] apply = fun.apply(5);
		System.out.println(apply.length);
		
		Function<Integer, String[]> fun2 = String[]::new;
		System.out.println(fun2.apply(12).length);
	}
	
	

	// 构造器引用
	@Test
	public void test5() {
		Supplier<Employee> sup = () -> new Employee();
		// 构造器引用方式
		Supplier<Employee> sups = Employee::new;
		
		Function<String, Employee> fun = (x) -> new Employee(x);
		Function<String, Employee> fun1 = Employee::new;
		System.out.println(fun1.apply("11"));
		
		BiFunction<String, Integer, Employee> bi = Employee::new;
		System.out.println(bi.apply("哈哈", 1));
	}
	
	
	// 类::实例方法名
	@Test
	public void test4() {
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		BiPredicate<String, String> bp2 = String::equals;
		
		Function<Employee, String> fun = Employee::getName;
		String apply = fun.apply(new Employee("呵呵"));
		System.out.println(apply);
	}
	
	
	// 类::静态方法名
	@Test
	public void test3() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		
		Comparator<Integer> com1 = Integer::compare;
		System.out.println(com1.compare(2, 6));
	}
	
	
	// 对象::实例方法名
	@Test
	public void test1() {
		Consumer<String> con = (x) -> System.out.println(x);

		// 方法引用
		PrintStream p = System.out;
		Consumer<String> con1 = p::println;
		Consumer<String> con2 = System.out::println;
		con2.accept("aaa");
	}

	@Test
	public void test2() {
		Employee emp = new Employee();
		Supplier<String> sup = () -> emp.getName();
		String str = sup.get();
		System.out.println(str);

		Supplier<Integer> sup2 = emp::getAge;
		System.out.println(sup2.get());
	}

}
