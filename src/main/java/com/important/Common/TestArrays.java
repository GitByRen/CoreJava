package com.important.Common;

import java.util.Arrays;
import java.util.List;

public class TestArrays {
	public static void main(String[] args) {
		int[] array = new int[5];
		// 填充数组
		Arrays.fill(array, 5);
		System.out.println("填充数组：Arrays.fill(array, 5)：");
		TestArrays.output(array);

		// 将数组的第2和第3个元素赋值为8,搜索指定的int型数组的范围
		Arrays.fill(array, 2, 4, 8);
		System.out.println("将数组的第2和第3个元素赋值为8：Arrays.fill(array, 2, 4, 8)：");
		TestArrays.output(array);

		int[] array1 = { 7, 8, 3, 2, 12, 6, 3, 5, 4 };
		// 对数组的第2个到第6个进行排序进行排序
		Arrays.sort(array1, 2, 7);
		System.out.println("对数组的第2个到第6个元素进行排序：Arrays.sort(array,2,7)：");
		TestArrays.output(array1);

		// 对整个数组进行排序
		Arrays.sort(array1);
		System.out.println("对整个数组进行排序：Arrays.sort(array1)：");
		TestArrays.output(array1);

		// 比较数组元素是否相等
		System.out.println("比较数组元素是否相等:Arrays.equals(array, array1):" + "\n" + Arrays.equals(array, array1));
		int[] array2 = array1.clone();
		System.out.println("克隆后数组元素是否相等:Arrays.equals(array1, array2):" + "\n" + Arrays.equals(array1, array2));

		// 使用二分搜索算法查找指定元素所在的下标（必须是排序好的，否则结果不正确）
		Arrays.sort(array1);
		System.out.println("元素3在array1中的位置：Arrays.binarySearch(array1, 3)：" + "\n" + Arrays.binarySearch(array1, 3));
		// 如果不存在就返回负数
		System.out.println("元素9在array1中的位置：Arrays.binarySearch(array1, 9)：" + "\n" + Arrays.binarySearch(array1, 9));

		// 将数组转换成集合,这里的ArrayList不是java.util.ArrayList，而是Arrays类的一个内部类，没有add()
		Integer[] arrays = new Integer[] { 1, 2, 3, 4, 5 };
		// 泛型只针对引用数据类型,默认是Object类型,int[]类型数组不是基本数据类型,不会触发autoboxing,直接就把数组作为一个Object传给asList方法了。
		int[] a = new int[] { 1, 2 };
		List asList = Arrays.asList(a);
		for (Object object : asList) {
			System.out.print(object);
		}
		
	}

	public static void output(int[] array) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i] + " ");
			}
		}
		System.out.println();
	}
}