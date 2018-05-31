package com.important.Inner;

public class InnerClass1 {
	public static void main(String[] args) {
		Outer outer = new Outer();
		Comparable comparable = outer.getComparable();
		comparable.compareTo(new Object());
	}
}

class Outer {
	int a = 10;
	
	// 局部内部类
	public Comparable getComparable() {
		final int b = 10;
		class MyComparable implements Comparable {
			@Override
			public int compareTo(Object o) {
				System.out.println(b);
				return 0;
			}
		}
		return new MyComparable();
	}

	public Comparable getComparable1() {
		return new Comparable() {
			@Override
			public int compareTo(Object o) {
				return 0;
			}
		};
	}
}