package com.important.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCollections {

	/**
	 * 操作Collection以及Map的工具类，Collections
	 * 
	 * 面试：区分Collection以及Collections
	 */
	
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(123);
		list.add(456);
		list.add(43);
		list.add(3);
		System.out.println(list);
		Collections.reverse(list);
		System.out.println(list);
		Collections.shuffle(list); //随机排序
		System.out.println(list);
		Collections.sort(list);  //升序排序
		System.out.println(list);
		Collections.swap(list, 0, 2);
		System.out.println(list);
		
		System.out.println("****************");
		
		Object max = Collections.max(list);
		System.out.println(max);
		int count = Collections.frequency(list, 456);  //返回指定集合中指定元素的出现次数
		System.out.println(count);
		
//		List list1 = new ArrayList();  // 错误的实现方式 因为长度为0，故报错，必须长度一样
		List<Integer> list1 = Arrays.asList(1,2,3,4,5);
		Collections.copy(list1, list);
		System.out.println(list1);
	}
	
}
