package com.important.Reflect.Call;

import java.lang.reflect.Method;

import org.junit.Test;

import com.important.Reflect.Person;

public class CallMethod {

	public static void main(String[] args) {
		for (String string : args) {
			System.out.println(string);
		}
	}

	@Test
	public void test2() {
		try {
			String strs = "com.important.Reflect.Call.CallMethod";
			Method method = Class.forName(strs).getMethod("main", String[].class);
			// 如果给可变参数传递数组，则可变参数的个数为数组的长度，因为invoke方法有可变参数
			method.invoke(null, new Object[] { new String[] { "1", "bv" } });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用运行时类中指定的方法
	 */
	@Test
	public void test1() throws Exception {
		Class clazz = Person.class;
		Person p = (Person) clazz.newInstance();
		// getMethod(String methodName,Class ... params):获取运行时类中声明为public的方法
		Method method = clazz.getMethod("show");
		// 调用指定的方法：Object invoke(Object obj,Object ... obj)
		Object returnVal = method.invoke(p);
		System.out.println(returnVal);

		Method method2 = clazz.getMethod("toString");
		Object invoke = method2.invoke(p);
		System.out.println(invoke);

		// 静态方法调用
		Method method3 = clazz.getMethod("info");
		method3.invoke(Person.class);

		// getDeclaredMethod(String methodName,Class ... params):获取运行时类中指定的方法
		Method method4 = clazz.getDeclaredMethod("display", String.class, Integer.class);
		method4.setAccessible(true);
		Object invoke2 = method4.invoke(p, "CHINA", 10);
		System.out.println(invoke2);
	}
}
