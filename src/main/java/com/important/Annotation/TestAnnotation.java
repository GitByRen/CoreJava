package com.important.Annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * 注解： 
 * 1.jdk提供的常用的注解
 * @override:限定重写父类方法，该注释只能用于方法
 * @deprecated:用于表示某个元素（类、方法）已过时
 * @suppressWarnings:抑制编译器警告
 * 2.自定义一个注解
 * 3.元注解:修饰注解的注解
 * ①@Retention ②@Target ③@Documented ④Inherited
 */
public class TestAnnotation {
	public static void main(String[] args) {
		@SuppressWarnings({"rawtypes","unused"})
		List list = new ArrayList();
	}
}

@MyAnnotation("atguigu")
class Sudent{
	
}