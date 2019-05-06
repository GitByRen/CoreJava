package com.important.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock2 {

	private Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		TestLock2 test = new TestLock2();
		MyThread thread1 = new MyThread(test, "A");
		MyThread thread2 = new MyThread(test, "B");
		thread1.start();
		thread2.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.interrupt();
	}

	public void insert(Thread thread) throws InterruptedException {
		// 注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将 InterruptedException 抛出
		lock.lockInterruptibly();

		try {
			System.out.println("线程" + thread.getName() + "得到了锁...");

			long startTime = System.currentTimeMillis();
			for (;;) {
				if (System.currentTimeMillis() - startTime >= 10000) {
					break;
				}
			}
		} finally {
			System.out.println(Thread.currentThread().getName() + "执行finally...");
			lock.unlock();
			System.out.println("线程 " + thread.getName() + "释放了锁");
		}
	}
}

class MyThread extends Thread {

	private TestLock2 testLock2 = null;

	public MyThread(TestLock2 test, String name) {
		super(name);
		this.testLock2 = test;
	}

	@Override
	public void run() {
		try {
			testLock2.insert(Thread.currentThread());
		} catch (InterruptedException e) {
			System.out.println("线程 " + Thread.currentThread().getName() + "被中断...");
		}
	}

}
