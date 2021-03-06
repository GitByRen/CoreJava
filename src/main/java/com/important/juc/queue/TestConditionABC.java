package com.important.juc.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AA打印5次，BB打印10次，CC打印15次，来10轮
 */
public class TestConditionABC {

	public static void main(String[] args) {
		ShareResource sr = new ShareResource();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				sr.print5();
			}
		}, "AA").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				sr.print10();
			}
		}, "BB").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				sr.print15();
			}
		}, "CC").start();
	}

}

class ShareResource {

	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print5() {
		lock.lock();
		try {
			while (number != 1) {
				c1.await();
			}

			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}

			number = 2;
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print10() {
		lock.lock();
		try {
			while (number != 2) {
				c2.await();
			}

			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}

			number = 3;
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print15() {
		lock.lock();
		try {
			while (number != 3) {
				c3.await();
			}

			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}

			number = 1;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}