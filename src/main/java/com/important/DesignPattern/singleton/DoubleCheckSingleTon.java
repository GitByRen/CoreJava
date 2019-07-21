package com.important.DesignPattern.singleton;

public class DoubleCheckSingleTon {

	private DoubleCheckSingleTon() {
		if (singleTon != null) {
			throw new RuntimeException("禁止反射！");
		}
	}

	private static volatile DoubleCheckSingleTon singleTon;

	public static DoubleCheckSingleTon getInstance() {
		if (singleTon == null) {
			synchronized (DoubleCheckSingleTon.class) {
				if (singleTon == null) {
					singleTon = new DoubleCheckSingleTon();
				}
			}
		}
		return singleTon;
	}

	// JVM从内存中反序列化地"组装"一个新对象时,就会自动调用这个 readResolve方法来返回我们指定好的对象了, 单例规则也就得到了保证.
	public Object readResolve() {
		return getInstance();
	}

}
