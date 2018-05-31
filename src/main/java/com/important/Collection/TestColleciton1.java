package com.important.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TestColleciton1 {

	public static void main(String[] args) {
		Collection coll = new ArrayList<>();
		List<Integer> asList = Arrays.asList(1,2,3);
		coll.addAll(asList);
		System.out.println(coll);
		Collection coll1 = new ArrayList<>();
		coll1.add(1);
		coll1.add(2);
		// 1.contains判断的依据：根据元素所在类的equals方法进行判断
		// 2.明确：如果存入集合中的元素是自定义类的对象。要求，自定义类要重写equals()方法
		boolean b1 = coll.contains(1);
		System.out.println(b1);
		boolean b2 = coll.containsAll(coll1);
		System.out.println(b2);
		// 3.retailAll(Collection coll):取两个集合的交集
		coll.retainAll(coll1);
		System.out.println(coll);
		// 4.remove(Object o) clear()
		boolean remove = coll.remove(99);
		System.out.println(remove);
		// 5.从当前集合去除掉共有部分
		coll.removeAll(coll1);
		System.out.println(coll);
		// 6.判断集合中的所有元素是否完全相同
		Collection coll2 = new ArrayList<>();
		coll2.add(1);
		coll2.add(3);
		System.out.println(coll.equals(coll2));
		// 7.将集合转换为数组
		Object[] obj = coll2.toArray();
		for (Object object : obj) {
			System.out.println("toArray:"+object);
		}
		// 8.iterator：返回一个Iterator接口实现类的对象
		Iterator iterator = coll2.iterator();
//		System.out.println("iterator:"+iterator.next());
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		// 9.增强for循环
		for (Object i : coll2) {
			System.out.println(i);
		}
		
	}

}
