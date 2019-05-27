package com.important.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、公平锁和非公平锁：
 *  1.1 并发包中ReentrantLock的创建可以指定构造函数的boolean类型来得到公平锁或非公平锁，默认为非公平锁
 *  1.2 公平锁：是指多个线程按照申请锁的顺序来获取锁
 *  1.3 非公平锁：是指多个线程获取锁的顺序并不是按照申请锁的顺序，上来就直接尝试占有锁，如果尝试失败，就再采用类似公平锁那种方式
 * 
 * 2、可重入锁（递归锁）：指的是同一线程外层函数获取锁之后，内层递归函数仍然能获取该锁
 *  2.1 也即是说：线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *  2.2 ReentrantLock/synchronized就是一个典型的可重入锁
 *  2.3 可重入锁最大的作用就是避免死锁
 */
public class FairLockAndReentrantLock {

	public static void main(String[] args) {
		SynchronizedDemo syn = new SynchronizedDemo();
		new Thread(() -> {
			syn.sendSms();
		}, "t1").start();
		
		new Thread(() -> {
			syn.sendSms();
		}, "t2").start();
		
		System.out.println("**************************");
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		LockDemo lockDemo = new LockDemo();
		new Thread(lockDemo).start();
		new Thread(lockDemo).start();
	}
	
}

class SynchronizedDemo {
	public synchronized void sendSms() {
		System.out.println(Thread.currentThread().getName() + "\t invoked sendSms()");
		sendEmail();
	}
	
	public synchronized void sendEmail() {
		System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
	}
}

class LockDemo implements Runnable {
	Lock lock = new ReentrantLock();

	@Override
	public void run() {
		send1();
	}
	
	public void send1() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t invoked send1()");
			send2();
		} finally {
			lock.unlock();
		}
	}
	
	public void send2() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t invoked send2()");
		} finally {
			lock.unlock();
		}
	}
	
}