package com.important.juc.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 一个线程写，多个线程读
 * 
 * 写写/读写 需要互斥
 * 读读不需要互斥
 *
 * 注意：
 * 如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。
 * 如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程也会一直等待释放写锁。
 */
public class TestReadWriteLock {

	public static void main(String[] args) {
		ReadWriteLockDemo rw = new ReadWriteLockDemo();

		new Thread(new Runnable() {
			@Override
			public void run() {
				rw.set((int) (Math.random() * 101));
			}
		}, "Write：").start();

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					rw.get();
				}
			}).start();
		}
	}

}

class ReadWriteLockDemo {

	private int num = 0;

	private ReadWriteLock rw = new ReentrantReadWriteLock();

	/**
	 * 读
	 */
	public void get() {
		rw.readLock().lock();

		try {
			System.out.println(Thread.currentThread().getName() + " ：" + num);
		} finally {
			rw.readLock().unlock();
		}
	}

	/**
	 * 写
	 */
	public void set(int num) {
		rw.writeLock().lock();

		try {
			System.out.println(Thread.currentThread().getName());
			this.num = num;
		} finally {
			rw.writeLock().unlock();
		}

	}

}