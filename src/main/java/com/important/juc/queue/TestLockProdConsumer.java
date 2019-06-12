package com.important.juc.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 */
public class TestLockProdConsumer {

	public static void main(String[] args) {
		ShareData share = new ShareData();

		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				share.increment();
			}
		}, "AA").start();

		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				share.decrement();
			}
		}, "BB").start();
	}

}

class ShareData {

	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() {
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}

			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void decrement() {
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}

			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}