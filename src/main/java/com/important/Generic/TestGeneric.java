package com.important.Generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {

	/**
	 * 自定义泛型类、泛型接口、泛型方法
	 */
	public static void main(String[] args) {
		Order<Boolean> order = new Order<Boolean>();
		order.setT(true);
		System.out.println(order.getT());
		order.add();
		List<Boolean> list = order.list;
		System.out.println(list);

		System.out.println("------------我是分割线---------------");

		SubOrder o = new SubOrder();
		List<Integer> list2 = o.list;
		System.out.println(list2);

		System.out.println("------------我是分割线---------------");

		// 当通过对象调用泛型方法时，指明泛型方法的类型
		Integer e = order.getE(3);
		Double e2 = order.getE(3.2);
		System.out.println(e + e2);

		System.out.println("------------我是分割线---------------");

		Integer[] in = new Integer[] { 1, 2, 3 };
		List<Integer> lists = new ArrayList<>();
		List<Integer> fromArrayToList = order.fromArrayToList(in, lists);
		System.out.println(fromArrayToList);
		
	}

}
