package com.important.Collection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectCollection {

	public static Integer getCapacity(ArrayList list) {
		Class<? extends List> clazz = list.getClass();
		Integer length = null;
		try {
			Field field = clazz.getDeclaredField("elementData");
			field.setAccessible(true);
			Object[] object = (Object[]) field.get(list);
			length = object.length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		Integer capacity = getCapacity(list);
		System.out.println("容量：" + capacity);
		System.out.println("大小：" + list.size());
	}

}
