package com.important.Reflect;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

public class TestReflection {

	@Test
	public void test5() {
		// 系统类加载器
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println(systemClassLoader);
		// 扩展类加载器
		ClassLoader parent = systemClassLoader.getParent();
		System.out.println(parent);
		// 引导类加载器(C++编写，该加载器无法直接获取，负责加载java的核心类)
		ClassLoader parent2 = parent.getParent();
		System.out.println(parent2);

		// 得到系统类加载器
		Class clazz = Person.class;
		ClassLoader classLoader = clazz.getClassLoader();
		System.out.println(classLoader);

		// 核心类不通过系统类加载器加载
		String className = "java.lang.String";
		try {
			Class clazz2 = Class.forName(className);
			ClassLoader classLoader2 = clazz2.getClassLoader();
			System.out.println(classLoader2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 读取文件
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream resourceAsStream = loader.getResourceAsStream("com/important/Reflect/jdbc.properties");
		try {
			// FileInputStream读取的相对路径针对的是项目根目录的
			// FileInputStream fis = new FileInputStream("src/com/yonyou/Reflect/jdbc.properties");
			Properties pro = new Properties();
			pro.load(resourceAsStream);
			String name = pro.getProperty("user");
			String pass = pro.getProperty("pass");
			System.out.println(name + pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Class的实例
	 */
	@Test
	public void test2() throws ClassNotFoundException {
		// 1.通过运行时类本身的.class属性
		Class clazz1 = Person.class;
		System.out.println(clazz1.getName());

		// 2.通过运行时类的对象获取
		Person p = new Person();
		Class<? extends Person> clazz2 = p.getClass();
		System.out.println(clazz2);

		// 3.通过Class的静态方法获取
		try {
			Class clazz3 = Class.forName("com.important.Reflect.Person");
			System.out.println(clazz3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 4.通过类的加载器(了解)
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz4 = classLoader.loadClass("com.important.Reflect.Person");
		System.out.println(clazz4);
	}

	@Test
	public void test1() throws Exception {
		// 类加载器读取.class字节码文件将其转换成java.lang.Class类的一个实例
		Class<Person> clazz = Person.class;

		// 1.创建clazz对应的运行时类Person类的对象，java9之后过期
//		Person p = clazz.newInstance();
		// java9之后
		Person p = clazz.getDeclaredConstructor().newInstance();

		// 2.通过反射调用运行时类的指定的属性
		// 2.1 获取public的属性
		Field f1 = clazz.getField("name");
		f1.set(p, "LiuDeHua");
		System.out.println(p);
		// 2.2 获取private的属性
		Field f2 = clazz.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 20);
		System.out.println(p);

		// 3.通过反射调用运行时类的指定方法
		Method m1 = clazz.getMethod("show");
		m1.invoke(p);

		Method m2 = clazz.getMethod("display", String.class);
		m2.invoke(p, "China");
	}

}
