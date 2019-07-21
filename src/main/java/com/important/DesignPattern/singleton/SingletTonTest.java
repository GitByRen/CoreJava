package com.important.DesignPattern.singleton;

import java.lang.reflect.Constructor;

/**
 * 1、你的单例线程安全吗? 
 * 2、你的单例反射安全吗? 
 * 3、你的单例序列化安全吗?
 */
public class SingletTonTest {

	public static void main(String[] args) {
		DoubleCheckSingleTon singleton1 = DoubleCheckSingleTon.getInstance();
		DoubleCheckSingleTon singleton2 = null;

		try {
			Class<DoubleCheckSingleTon> clazz = DoubleCheckSingleTon.class;
			Constructor<DoubleCheckSingleTon> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			singleton2 = constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(singleton1.hashCode());
		System.out.println(singleton2.hashCode());
	}

}
