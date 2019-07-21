package com.important.DesignPattern.singleton;

// 非线程安全
public class LazySingleTon {

	private LazySingleTon() {
	}

	private static LazySingleTon lazySingleTon;

	// 懒汉式
	public static LazySingleTon getInstance() {
		if (lazySingleTon == null) {
			lazySingleTon = new LazySingleTon();
		}
		return lazySingleTon;
	}

}
