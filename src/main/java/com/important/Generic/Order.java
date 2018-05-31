package com.important.Generic;

import java.util.ArrayList;
import java.util.List;

// 自定义泛型类
public class Order<T> {

	private int orderId;
	private String orderName;
	private T t;
	List<T> list = new ArrayList<>();
	
	// 泛型方法
	public <E> E getE(E e) {
		return e;
	}

	// 实现数组到集合的复制
	public <E> List<E> fromArrayToList(E[] e,List<E> list) {
		for (E e1 : e) {
			list.add(e1);
		}
		return list;
	}
	
	public void add() {
		list.add(t);
	}
	
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	// 不能在静态方法中使用泛型
//	public static void show() {
//		System.out.println(t);
//	}
	
	public void info() {
		// 泛型不能用于异常
//		try {
//			
//		}catch(T e){
//			
//		}
	}
}

class SubOrder extends Order<Integer>{
	
}

