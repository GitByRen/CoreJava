package com.important.DesignPattern.proxy;

/**
 * 静态代理
 */
public class TestStaticProxy {

	public static void main(String[] args) {
		StaticAction obj = new ProxyObject();
		obj.action();
	}

}

interface StaticAction {
	void action();
}

class ObjectImpl implements StaticAction {
	@Override
	public void action() {
		System.out.println("==被代理类开始执行==");
		System.out.println("==具体操作==");
		System.out.println("==被代理类执行完毕==");
	}
}

class ProxyObject implements StaticAction {
	StaticAction obj;

	public ProxyObject() {
		System.out.println("代理类创建成功");
		obj = new ObjectImpl();
	}

	@Override
	public void action() {
		System.out.println("代理类开始执行");
		obj.action();
		System.out.println("代理类执行结束");
	}
}