package com.important.DesignPattern.Proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.Object;

public class TestDynamicProxyArgs {
	public static void main(String[] args) {
		MyInvocationHandlers m = new MyInvocationHandlers();
		Subjects proxyInstance = m.getProxyInstanceArray();
		proxyInstance.aa(10).aa(2);
	}
}

interface Subjects {
	Subjects aa(Integer balance);
}

class MyInvocationHandlers implements InvocationHandler {
	
	/**
	 * proxy:①可以使用反射获取代理对象的信息，也就是proxy.getClass().getName()
	 * ②可以将代理对象返回以进行连续调用，这就是proxy存在的目的，因为this并不是代理对象
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ("aa".equals(method.getName())) {
			Integer value = (Integer) args[0];
			System.out.println("deposit: " + value);
			return proxy;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <E> E getProxyInstanceArray() {
		return (E) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[] { Subjects.class, Serializable.class }, this);
	}

}
