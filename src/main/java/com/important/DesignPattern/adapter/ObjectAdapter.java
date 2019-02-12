package com.important.DesignPattern.adapter;

// 对象适配器模式
public class ObjectAdapter {

}

interface Targets {
	/**
	 * 这是源类Adaptee也有的方法
	 */
	public void sampleOperation1();

	/**
	 * 这是源类Adapteee没有的方法
	 */
	public void sampleOperation2();
}

class Adaptees {

	public void sampleOperation1() {
	}

}

class Adapters {
	private Adaptee adaptee;

	public Adapters(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	/**
	 * 源类Adaptee有方法sampleOperation1 因此适配器类直接委派即可
	 */
	public void sampleOperation1() {
		this.adaptee.sampleOperation1();
	}

	/**
	 * 源类Adaptee没有方法sampleOperation2 因此由适配器类需要补充此方法
	 */
	public void sampleOperation2() {
		// 写相关的代码
	}
}