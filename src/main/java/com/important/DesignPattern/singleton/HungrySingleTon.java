package com.important.DesignPattern.singleton;

// 没有延迟加载
public class HungrySingleTon {

	// 1.私有化构造器
	private HungrySingleTon() {
	}

	// 2.类的内部创建实例
	private static final HungrySingleTon hungrySingleTon = new HungrySingleTon();

	// 3.公有方法返回
	public static HungrySingleTon getInstance() {
		return hungrySingleTon;
	}

}