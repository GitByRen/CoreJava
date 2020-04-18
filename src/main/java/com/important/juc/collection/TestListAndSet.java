package com.important.juc.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TestListAndSet {

	public static void main(String[] args) {
		/**
		 * 1、故障现象：
		 *    java.util.ConcurrentModificationException
		 * 
		 * 2、故障原因：
		 *    ArrayList的add方法非线程安全
		 * 
		 * 3、解决方案：
		 *    3.1 new Vector<>();
		 *    3.2 Collections.synchronizedList(new ArrayList<>());
		 *    3.3 new CopyOnWriteArrayList<>();
		 *    
		 * 4、区别
		 *    CopyOnWriteArrayList的写操作性能较差，而多线程的读操作性能较好
		 *    Collections.synchronizedList的写操作性能比CopyOnWriteArrayList在多线程操作的情况下要好很多，
		 *            而读操作因为是采用了synchronized关键字的方式，其读操作性能并不如CopyOnWriteArrayList
		 */
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			}).start();
		}
		
	}
	
	/**
	 * Collections.synchronizedSet(new HashSet<>());
	 * CopyOnWriteArraySet()
	 */
	public void copyOnWriteArraySet() {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			}).start();
		}
	}
	
	private void testRemove() {
		// 能执行成功，只循环一次，因为remove后size变为1，hasNext会判断cursor != size后返回
		List<String> arrayList1 = new ArrayList<String>();
		arrayList1.add("1");
		arrayList1.add("2");
		for (String s : arrayList1) {
		    if ("1".equals(s)) {
		        arrayList1.remove(s);
		    }
		}
		// 报错，在循环第二次时next方法会检查modCount和expectedModCount是否相同，然后抛出异常
		List<String> arrayList2 = new ArrayList<String>();
		arrayList2.add("2");
		arrayList2.add("1");
		for (String s : arrayList2) {
		    if ("1".equals(s)) {
		        arrayList2.remove(s);
		    }
		}
	}
}
