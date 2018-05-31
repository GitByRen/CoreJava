package com.important.Thread;

/**
 * 对比Thread和Runnable
 * 1.class Thread implements Runnable
 * 2.实现的方式优于继承的方式
 * 	 why? ①实现的方式避免了单继承的局限性
 * 		  ②如果多个线程要操作同一份资源，更适合用实现的方式
 */
public class TestRunnable {
	public static void main(String[] args) {
		Print p = new Print();
		// 要想启动多线程必须使用start()
		new Thread(p).start();
		new Thread(p).start();
	}
}

class Print implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 0) {
				System.out.println(Thread.currentThread().getName() + ":" + i);
			}
		}
	}
}
