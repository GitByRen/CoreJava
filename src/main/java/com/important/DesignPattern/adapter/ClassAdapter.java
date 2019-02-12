package com.important.DesignPattern.adapter;

// 类适配器模式
public class ClassAdapter {

}

// 目标角色
interface Target {
	/**
	 * 这是源类Adaptee也有的方法
	 */
	public void sampleOperation1();

	/**
	 * 这是源类Adapteee没有的方法
	 */
	public void sampleOperation2();
}

// 需要适配的类
class Adaptee {
    
    public void sampleOperation1(){}

}

// 适配器角色
class Adapter extends Adaptee implements Target {

	@Override
	public void sampleOperation2() {
		
	}
	
}