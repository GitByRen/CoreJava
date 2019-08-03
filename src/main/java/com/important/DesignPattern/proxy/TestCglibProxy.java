package com.important.DesignPattern.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 1、对于不需要实现接口的类可以使用cglib代理 
 * 2、目标对象的方法如果是final/static，那么就不会拦截
 */
public class TestCglibProxy {
	public static void main(String[] args) {
		Programmer programmer = new Programmer();
		Hacker hacker = new Hacker();
		// cglib 中加强器，用来创建动态代理
		Enhancer enhancer = new Enhancer();
		
		// 设置要创建动态代理的类
		enhancer.setSuperclass(programmer.getClass());
		// 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
		enhancer.setCallback(hacker);
		Programmer proxy = (Programmer) enhancer.create();
		proxy.code();
	}
}

class Programmer {
	public void code() {
		System.out.println("I'm a Programmer,Just Coding.....");
	}
}

class Hacker implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
		proxy.invokeSuper(obj, args);
		System.out.println("****  Oh,what a poor programmer.....");
		return null;
	}

}