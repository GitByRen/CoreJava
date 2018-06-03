package com.important.Collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class TestCollection3 {

	/**
	 * Map: |----HashMap 
	 *      |----LinkedHashMap 
	 *      |----TreeMap 
	 *      |----HashTable
	 * 			|----Properties：常用来处理属性文件
	 * 
	 * 1.key不可以重复,value可以重复 2.key使用set来存的，不可重复
	 * 3.一个key-value对是一个Entry,所有的Entry是用Set来存放的，也是不可重复的。
	 * 4.向HashMap中添加元素时，会调用key所在类的equals()方法，判断两个key是否相同， 若相同只能添加进后添加的那个元素。
	 */

	@Test
	public void test1() {
		Map map = new HashMap();
		map.put("AA", 213);
		map.put("BB", 132);
		map.put(new Person("AA", 12), null);
		map.put("CC", 456);
		map.put("DD", "CC");

//		for (String key : map.keySet()) {
//		    value = map.get(key);
//		}
		// map没有泛型则报错
//		for (Entry<String, String> entry: map.entrySet()) {  
//		    key = entry.getKey();  
//		    value = entry.getValue();  
//		}
//		for (String value : map.values()) {  
//		}
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

	@Test
	public void test2() {
		/**
		 * TreeMap:按照添加进Map中的元素的key的指定属性进行排序，所以必须是同一个类 的对象
		 */
		// 自然排序
		Map map = new TreeMap();
		map.put(new Person("AA", 23), 12);
		map.put(new Person("JJ", 13), 32);
		map.put(new Person("GG", 22), 43);
		map.put(new Person("BB", 33), 7);
		Set set1 = map.keySet();
		for (Object obj : set1) {
			System.out.println(obj + "-------" + map.get(obj));
		}

		System.out.println("***************");

		// 定制排序
		Map<Customer,Integer> map1 = new TreeMap<Customer,Integer>(new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof Customer && o2 instanceof Customer) {
					Customer c1 = (Customer) o1;
					Customer c2 = (Customer) o2;
					int i = c1.getId().compareTo(c2.getId());
					if (i == 0) {
						return c1.getName().compareTo(c2.getName());
					}
					return i;
				}
				return 0;
			}
		});
		map1.put(new Customer("AA", 23), 12);
		map1.put(new Customer("JJ", 13), 32);
		map1.put(new Customer("GG", 22), 43);
		map1.put(new Customer("BB", 33), 7);
		Set set2 = map.keySet();
		for (Object obj : set2) {
			System.out.println(obj + "-------" + map.get(obj));
		}

	}
	
	@Test
	public void test3() {
		Properties pros = new Properties();
		try {
			pros.load(new FileInputStream(new File("jdbc.properties")));
			String user = pros.getProperty("username");
			String pasd = pros.getProperty("password");
			System.out.println(user + "--" + pasd);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

