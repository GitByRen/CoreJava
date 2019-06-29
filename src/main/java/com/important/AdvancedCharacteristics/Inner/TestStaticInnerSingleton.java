package com.important.AdvancedCharacteristics.Inner;

import java.lang.reflect.Constructor;

public class TestStaticInnerSingleton {

	public static void main(String[] args) {
		StaticInnerSingleton singleton1 = StaticInnerSingleton.getInstance();
		StaticInnerSingleton singleton2 = null;

		Class clazz = StaticInnerSingleton.class;
		try {
			Constructor<StaticInnerSingleton> declaredConstructor = clazz.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			singleton2 = declaredConstructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 反射破坏了单例
		System.out.println(singleton1.hashCode());
		System.out.println(singleton2.hashCode());
	}

}

/**
 * 你的单例线程安全吗? 
 * 你的单例反射安全吗？ 
 * 你的单例序列化安全吗？
 */
class StaticInnerSingleton {

	private StaticInnerSingleton() {
		if (SingletonHolder.mInstance != null) {
			throw new RuntimeException("想反射我，没门！");
		}
		System.out.println("单例");
	}

	private static class SingletonHolder {
		private static final StaticInnerSingleton mInstance = new StaticInnerSingleton();
	}

	public static StaticInnerSingleton getInstance() {
		return SingletonHolder.mInstance;
	}

}
