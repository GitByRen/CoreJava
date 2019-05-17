package com.important.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.i++的原子性问题 int i = 10; i = i++; // 10
 * 
 * 以上可以拆解成三步：读->改->写 
 * 
 * int temp = i; 
 * i = i + 1; 
 * i = temp;
 * 
 * 2.原子变量：jdk1.5后java.util.concurrent.atomic包下提供了常用的原子变量：
 * 		1）volatile保证内存可见性
 * 		2）CAS(Compare-And-Swap)算法保证数据的原子性
 * 			内存值V
 * 			预期值A
 * 			更新值B
 * 			当且仅当内存值V和预期值A相同时，将内存值V修改为B，否则什么都不做
 */
public class TestAtomicDemo {

	public static void main(String[] args) {
		AtomicDemo atom = new AtomicDemo();

		for (int i = 0; i < 10; i++) {
			new Thread(atom).start();
		}
	}
}

class AtomicDemo implements Runnable {

	private int serialNumber = 0;
//	private AtomicInteger serialNumber = new AtomicInteger();

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + "：" + getSerialNumber());
	}

	public int getSerialNumber() {
		return serialNumber++;
//		return serialNumber.getAndIncrement();
	}
}