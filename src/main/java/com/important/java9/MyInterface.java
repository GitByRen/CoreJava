package com.important.java9;

public class MyInterface {

	public static void main(String[] args) {
		MyInterfaceTest my = new MyInterfaceTest();
		my.defaultMethod();
	}
	
}

interface Interface9 {
	
	// jdk7：只能声明全局变量(public static final)和抽象方法(public abstract)
	void method();
	
	// jdk8：声明静态方法 和 默认方法
	static void staticMethod() {
		
	}
	
	default void defaultMethod() {
		privateMethod();
	}
	
	// jdk9：声明私有方法
	private void privateMethod() {
		System.out.println("privateMethod");
	}
}

class MyInterfaceTest implements Interface9 {

	@Override
	public void method() {
		System.out.println("method");
	}
	
}