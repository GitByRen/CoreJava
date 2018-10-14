package com.important.java8.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * Lambda
 */
public class TestLambda {

	/**
	 * 情景一：无参无返回值
	 */
	@Test
	public void test1() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello World!");
			}
		};
		runnable.run();

		System.out.println("***************");

		Runnable r = () -> System.out.println("Hello Lambda");
		r.run();
	}

	/**
	 * 情景二：有一个参数无返回值(括号可以省略)
	 */
	@Test
	public void test2() {
		Consumer<Integer> con = new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println(t);
			}
		};
		con.accept(1);

		System.out.println("******************");

		Consumer<Integer> consumer = (e) -> System.out.println(e);
		consumer.accept(2);
	}

	/**
	 * 情景三：有两个及以上参数，参数类型可写可不写
	 */
	@Test
	public void test3() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		System.out.println(com.compare(6, 3));

		System.out.println("********************");

		// Lambda 体中有多条语句
		Comparator<Double> comparator1 = (x, y) -> {
			System.out.println("函数式接口");
			return Double.compare(x, y);
		};
		System.out.println(comparator1.compare(2.4, 0.1));

		System.out.println("*********************");

		// 若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
		Comparator<Integer> comparator2 = (x, y) -> Integer.compare(x, y);
		System.out.println(comparator2.compare(5, 8));
	}

}

