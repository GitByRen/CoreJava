package com.test;

import java.lang.reflect.Method;

public class MyAdvice implements Advice {

	@Override
	public void beforeMethod(Method method) {
		System.out.println("前置通知" + method.getName());
	}

	@Override
	public void afterMethod(Method method) {
		System.out.println("后置通知" + method.getName());
	}

}
