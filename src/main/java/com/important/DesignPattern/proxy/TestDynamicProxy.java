package com.important.DesignPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Subject {
	void action();
}

class RealSubject implements Subject {
	@Override
	public void action() {
		System.out.println("我是被代理类！");
	}
}

class MyInvocationHandler implements InvocationHandler {
	Object obj; // 实现了接口的被代理类的对象的声明

	// 1.给被代理类的对象实例化 2.返回一个代理类的对象
	public Object blind(Object obj) {
		this.obj = obj;
		/**
		 * ClassLoader:一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载.
		 * Class<?>[]:一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它,
		 * 那么这个代理对象就实现了该接口(多态)，这样我就能调用这组接口中的方法了.
		 * InvocationHandler:一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上.
		 */
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	// 当通过代理类的对象发起对被重写方法的调用时，都会转换为对如下invoke方法的调用
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// method方法的返回值是returnVal
		Object invoke = method.invoke(obj, args);
		return null;
	}
}

class TestDynamicProxy {
	public static void main(String[] args) {
		// 1.创建一个被代理类的对象
		RealSubject real = new RealSubject();
		// 2.创建一个实现了Invitation接口的类的对象
		MyInvocationHandler handler = new MyInvocationHandler();
		// 3.调用blind方法，返回代理类对象
		Object obj = handler.blind(real);
		Subject sub = (Subject) obj;
		sub.action();
	}
}