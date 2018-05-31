package com.important.Reflect.Obtain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.important.Reflect.Person;

public class TestMethod {

	/**
	 * 获取运行时类的方法
	 */
	@Test
	public void test1() {
		Class clazz = Person.class;
		// 1.getMethods()获取运行时类及其父类中所有的声明为public的方法
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			System.out.println(m);
		}

		System.out.println("***********");

		// 2.getDeclaredMethods()获取运行时类本身声明的所有的方法
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println(method);
		}
	}

	/**
	 * 注解 权限修饰符 方法名 返回值类型 参数列表 异常
	 */
	@Test
	public void test2() {
		Class clazz = Person.class;
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			// 1.获取注解
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations) {
				System.out.println(annotation);
			}

			// 2.权限修饰符
			int modifiers = method.getModifiers();
			String str = Modifier.toString(modifiers);
			System.out.print(str + " ");

			// 3.返回值类型
			Class returnType = method.getReturnType();
			System.out.print(returnType.getName() + " ");

			// 4.方法名
			System.out.print(method.getName() + " ");

			// 5.形参列表
			System.out.print("(");
			Class[] params = method.getParameterTypes();
			for (int i = 0; i < params.length; i++) {
				System.out.print(params[i].getName() + " ");
			}
			System.out.print(")");

			// 6.异常类型
			Class[] exceptionTypes = method.getExceptionTypes();
			for (int i = 0; i < exceptionTypes.length; i++) {
				System.out.print(exceptionTypes[i].getName() + " ");
			}

			System.out.println();
		}
	}

}
