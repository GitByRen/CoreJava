package com.test.AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyAopProxy {
	public static void main(String[] args) {
		Subject s = (Subject) getProxy(new Subject() {

			@Override
			public void show() {
				System.out.println("我是被代理的!!!");
			}
		}, new MyAdvice());
		s.show();
	}

	public static Object getProxy(Object target, Advice advice) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						advice.beforeMethod(method);
						Object invoke = method.invoke(target, args);
						advice.afterMethod(method);
						return invoke;
					}
				});

	}
}

interface Subject {
	public void show();
}

