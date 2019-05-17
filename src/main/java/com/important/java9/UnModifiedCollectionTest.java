package com.important.java9;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UnModifiedCollectionTest {

	/**
	 * jdk1.8及之前：创建只读集合
	 */
	@Test
	public void test1() {
		List<Integer> unmodifiableList = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
//		unmodifiableList.add(4);
		unmodifiableList.forEach(System.out::println);

		Set<Integer> unmodifiableSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3)));
		unmodifiableSet.add(4);
		unmodifiableSet.forEach(System.out::println);

		Map<Object, Object> unmodifiableMap = Collections.unmodifiableMap(new HashMap<>() {

			private static final long serialVersionUID = 1L;

			{
				put("tom", 1);
				put("jerry", 2);
				put("tj", 3);
			}

		});
//		unmodifiableMap.put("fg", 4);
		unmodifiableMap.forEach((k, v) -> System.out.println(k + ":" + v));
	}

	/**
	 * jdk1.9:创建只读集合
	 */
	@Test
	public void test2() {
		List<Integer> list = List.of(1, 2, 3);
		list.add(4);
		
		Set<Integer> set = Set.of(4, 5, 6);
		set.add(7);
		
		Map<String, Object> map = Map.of("a", 1, "b", "2");
//		map.put("c", 3);
		System.out.println(map);
	}
	
}
