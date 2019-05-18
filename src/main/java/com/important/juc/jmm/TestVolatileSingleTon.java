package com.important.juc.jmm;

public class TestVolatileSingleTon {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				VolatileSingleTon.getSingleTon();
			}).start();
		}
	}

}

class VolatileSingleTon {

	// 这里使用volatile禁止重排序
	private static volatile VolatileSingleTon volatileSingleTon = null;

	private VolatileSingleTon() {
		System.out.println("我是构造方法SingleTon");
	}

	public static VolatileSingleTon getSingleTon() {
		if (volatileSingleTon == null) {
			synchronized (VolatileSingleTon.class) {
				if (volatileSingleTon == null) {
					/**
					 * 这条语句实际上包含了三个操作： 1.分配对象的内存空间； 2.初始化对象(调用构造方法)； 3.设置instance指向刚分配的内存地址；
					 * 
					 * 由于jvm的"优化"，指令2和指令3的执行顺序是不一定的，当执行完指令3后， 此时的
					 * volatileSingleTon对象就已经不在是null的了,但此时指令2不一定已经被执行。
					 * 
					 * 假设线程1和线程2同时调用getSingleTon()方法，此时线程1执行完指令1和指令3，线程2抢到了执行权，此时volatileSingleTon
					 * 对象是非空的。所以线程2拿到了一个尚未初始化的volatileSingleTon对象，此时线程2调用这个volatileSingleTon就会抛出异常。
					 */
					volatileSingleTon = new VolatileSingleTon();
				}
			}
		}

		return volatileSingleTon;
	}

}