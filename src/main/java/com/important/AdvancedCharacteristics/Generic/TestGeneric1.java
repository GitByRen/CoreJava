package com.important.AdvancedCharacteristics.Generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class TestGeneric1 {

	public static void main(String[] args) {
		/*
		 *  泛型与继承的关系
		 *  若类A是类B的子类，那么List<A>就不是List<B>的子接口
		 */
		Object obj = null;
		String str = "AA";
		obj = str;
		
		List<Object> list1 = null;
		List<String> list2 = new ArrayList<String>();
//		list1 = list2;
		
		/**
		 * 通配符
		 * List<A>、List<B>都是List<?>的子类
		 * 
		 * ? extends A:可以存放A及其子类
		 * ? super A:可以存放A及其父类
		 */
		List<?> list = null;
		List<Object> list3 = new ArrayList<Object>();
		List<String> list4 = new ArrayList<String>();
		list = list3;
		list = list4;
		show(list3);
		show(list4);
		
		List<? extends Number> list5 = null;
		List<Integer> list6 = null;
		list5 = list6;
		List<? super Number> list7 = null;
		list7 = list3;
	}
	
	public static void show(List<?> list) {
		
	}
	
	public static <T> void copy(List<? super T> de) {
		
	}

	@Test
	public void test7() {
		List<String> list = new ArrayList<String>();
		list.add("AA");
		list.add("BB");
		List<?> list1 = list;
		
		Iterator<?> iterator = list1.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
//		list1.add("aa");   不允许向含有通配符的list中添加元素
		list1.add(null);  //可以存null
	}
	
	public class Pair<K, V> {
	    private K key;
	    private V value;
	    public Pair(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }
	    public void setKey(K key) { this.key = key; }
	    public void setValue(V value) { this.value = value; }
	    public K getKey()   { return key; }
	    public V getValue() { return value; }
	}
	
	/**
	 * @param anArray
	 * @param elem
	 * @return
	 */
//	因为除了short, int, double, long, float, byte, char等原始类型，其他的类并不一定能使用操作符>
	public static <T> int countGreaterThan(T[] anArray, T elem) {
	    int count = 0;
//	    for (T e : anArray)
//	        if (e > elem)  // compiler error  The operator > is undefined for the argument type(s) T, T
//	            ++count;
	    return count;
	}
//	这样就等于告诉编译器类型参数T代表的都是实现了Comparable接口的类，这样等于告诉编译器它们都至少实现了compareTo方法。
	public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
	    int count = 0;
	    for (T e : anArray)
	        if (e.compareTo(elem) > 0)
	            ++count;
	    return count;
	}
	
	
}
