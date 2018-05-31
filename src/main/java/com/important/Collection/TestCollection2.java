package com.important.Collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TestCollection2 {

	/**
	 * 集合：
	 *   Collection接口
	 *   		|--------List接口：存储有序的，可以重复的元素
	 *   					|-------ArrayList、LinkedList、Vector
	 *   		|--------Set接口：存储无序的，不可重复的元素
	 *   					|-------HashSet、LinkedHashSet、TreeSet
	 *   Map接口：存储键值对的数据
	 *   		|--------HashMap、LinkedHashMap、TreeMap、Hashtable
	 */
	
	@Test
	public void testCollction5() {
		/**
		 * TreeSet的定制排序
		 * 
		 * 1.创建一个Comparator接口的类的对象
		 */
		Comparator com = new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof Customer && o2 instanceof Customer) {
					Customer c1 = (Customer) o1;
					Customer c2 = (Customer) o2;
					int i = c1.getId().compareTo(c2.getId());
					if(i == 0) {
						return c1.getName().compareTo(c2.getName());
					}
					return i;
				}
				return 0;
			}
		};
		
		//2.将此对象作为形参传递给TreeSet
		TreeSet set = new TreeSet(com);
		set.add(new Customer("AA", 1002));
		set.add(new Customer("BB", 1001));
		set.add(new Customer("DD", 1008));
		set.add(new Customer("CC", 1005));
		set.add(new Customer("FF", 1003));
		for (Object object : set) {
			System.out.println(object);
		}
		
	}
	
	
	@Test
	public void testCollction4() {
		/**
		 * TreeSet：可以去重并排序
		 * 
		 * 1.向TreeSet中添加的元素必须是同一个类的
		 * 2.可以按照添加进集合中的元素指定的顺序遍历
		 * 3.当Person类没有实现Comparable接口时，向TreeSet中添加Person对象时，
		 * 会报ClassCastException.
		 * 4.当Comparable比较方式和Comparator比较方式同时存在时，以Comparator的比较方式为主；
		 */
		Set set = new TreeSet<>();
		set.add(new Person("BB",24));
		set.add(new Person("EE",23));
		set.add(new Person("FF",22));
		set.add(new Person("KK",34));
		set.add(new Person("AA",21));
		set.add(new Person("AA",21));
		set.add(new Person("AB",21));
		for (Object object : set) {
			System.out.println(object);
		}
	}
	
	@Test
	public void testCollction3() {
		/*
		 * LinkedHashSet：使用链表维护了一个添加进集合中的顺序，导致遍历集合元素时，
		 * 是按照添加进去的顺序遍历的！
		 */
		Set set = new LinkedHashSet<>();
		set.add(123);
		set.add(456);
		set.add(new String("AA"));
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	@Test
	public void testCollection2() {
		//2.HashSet：无序性不等于随机性。（无序性指的是元素在底层存储的位置是无序的） 
		/**
		 * 1.要求添加进set中的元素所在的类，一定要重写equals和hashCode方法。进而保证set中的元素的不可重复性。
		 * 2.当向set中添加对象时，首先调用此对象所在类的hashCode方法，计算此对象的哈希值，此哈希值决定了此对象在set中的存储位置。
		 * 3.元素的哈希值是通过元素的hashcode方法 来获取的, HashSet首先判断两个元素的哈希值，如果哈希值一样，接着会比较equals方法
		 * 如果 equls结果为true ，HashSet就视为同一个元素。如果equals 为false就不是同一个元素。
		 */
		Set set = new HashSet<>();
		set.add(123);
		set.add(null);
		set.add("AA");
		set.add(456);
		set.add(new Person("a",1));
		set.add(new Person("a",1));
		System.out.println(set);
	}
	
	@Test
	public void testCollection1() {
		//1.ArrayList
		List list = new ArrayList<>();
		list.add(123);
		list.add(456);
		list.add(null);
		list.add(new String("AA"));
		System.out.println(list);
		System.out.println(list.get(0));
		list.set(0, 1);
		System.out.println(list);
		list.remove(2);
		System.out.println(list);
		System.out.println(list.indexOf(456));
		System.out.println(list.subList(0, 1));
	}
}
