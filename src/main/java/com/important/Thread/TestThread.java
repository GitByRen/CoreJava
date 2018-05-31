package com.important.Thread;

/**
 * 创建一个子线程，完成1-100之间自然数的输出。同样的，主线程执行同样的操作。
 */
public class TestThread {
	public static void main(String[] args) {
		SubThread st = new SubThread();
		// 调用线程的start()：启动此线程;调用相应的run方法
		// 一个线程只能执行一次start
//		st.run();  主线程执行run方法，不会开启新线程
		st.start();
		for (int i = 1; i <= 100; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}
}

// 1.创建一个继承于Thread的子类
class SubThread extends Thread {
	// 2.重写run方法
	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}
}
