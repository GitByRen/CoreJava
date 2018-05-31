package com.important.Thread;

public class TestSingleTon {
	public static void main(String[] args) {
		SingleTon instance = SingleTon.getInstance();
		SingleTon instance2 = SingleTon.getInstance();
		System.out.println(instance == instance2);
	}
}

class SingleTon {
	private SingleTon() {
	}

	private static SingleTon singleTon = null;

	public static SingleTon getInstance() {
		// 这样提高效率
		if(singleTon == null) {
			// 这样保证线程安全
			synchronized(SingleTon.class) {
				if (singleTon == null) {
					singleTon = new SingleTon();
				}
			}
		}
		return singleTon;
	}
}