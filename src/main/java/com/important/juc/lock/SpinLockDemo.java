package com.important.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁：
 * 自旋锁是指当一个线程尝试获取某个锁时，如果该锁已被其他线程占用，就一直循环检测锁是否被释放，而不是进入线程挂起或睡眠状态。
 * 如果一直获取不到锁，那线程也不能一直占用cpu自旋做无用功，所以需要设定一个自旋等待的最大时间，自旋时间由JVM控制。
 * 如果超过该时间仍没有获取锁，线程会停止自旋进入阻塞状态。
 * 
 * 这样做的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 * 
 * 自旋锁适用于锁的竞争不激烈，且占用锁时间非常短的情况
 * 
 * 自旋锁本身无法保证公平性，同时也无法保证可重入性。
 * 
 */
public class SpinLockDemo {

	AtomicReference<Thread> atomicReference = new AtomicReference<>();

	public void myLock() {
		Thread thread = Thread.currentThread();
		System.out.println(Thread.currentThread().getName() + "\t come in...");

		// 自旋
		while (!atomicReference.compareAndSet(null, thread)) {
//			System.out.println("进来了...");
		}
	}

	public void myUnLock() {
		Thread thread = Thread.currentThread();
		atomicReference.compareAndSet(thread, null);
		System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock...");
	}

	public static void main(String[] args) {
		SpinLockDemo spinLockDemo = new SpinLockDemo();
		
		new Thread(() -> {
			try {
				spinLockDemo.myLock();
				TimeUnit.SECONDS.sleep(5);
				spinLockDemo.myUnLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "AA").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() -> {
			try {
				spinLockDemo.myLock();
				TimeUnit.SECONDS.sleep(1);
				spinLockDemo.myUnLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "BB").start();
	}

}
