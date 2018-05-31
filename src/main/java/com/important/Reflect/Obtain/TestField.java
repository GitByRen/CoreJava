package com.important.Reflect.Obtain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.important.Reflect.Person;

public class TestField {

	/**
	 * 获取运行时类的属性
	 */
	@Test
	public void test1() {
		Class clazz = Person.class;
		// 1.getFields()只能获取到运行时类及其父类中声明为public的属性
		Field[] fields = clazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i]);
		}

		// 2.getDeclaredFields()获得运行时类本身声明的所有的属性
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field.getName());
		}
	}

	/**
	 * 权限修饰符 变量类型 变量名
	 */
	@Test
	public void test2() {
		Class clazz = Person.class;
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			// 1.获取每个属性的权限修饰符
			int modifiers = field.getModifiers();
			String str = Modifier.toString(modifiers);
			System.out.print(str + " ");
			// 2.获取属性的变量类型
			Class type = field.getType();
			System.out.print(type.getName() + " ");
			// 3.获取属性名
			System.out.println(field.getName());
		}
	}

}
