package com.important.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jdk1.5以后：同步锁Lock：是一个显示锁，需要通过lock()方法上锁，通过unlock()方法释放锁
 * 
 * 1）tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true；如果获取失败（即锁已被其他线程获取），
 * 则返回false，也就是说，这个方法无论如何都会立即返回（在拿不到锁时不会一直在那等待）。
 * 
 * tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待
 * 一定的时间，在时间期限之内如果还拿不到锁，就返回false，同时可以响应中断。如果一开始拿到锁或者在等待期间内拿到了锁，
 * 则返回true。
 * 
 * 2）lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程 正在等待获取锁，则这个线程能够 响应中断，
 * 即中断线程的等待状态。例如，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，
 * 假若此时线程A获取到了锁，而线程B只能等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
 * 
 * 注意，当一个线程获取了锁之后，是不会被interrupt()方法中断的。因为interrupt()方法只能中断阻塞过程中的线程
 * 而不能中断正在运行过程中的线程。因此，当通过lockInterruptibly()方法获取某个锁时，如果不能获取到，
 * 那么只有进行等待的情况下，才可以响应中断的。与 synchronized 相比，当一个线程处于等待某个锁的状态，
 * 是无法被中断的，只有一直等待下去。
 * 
 */
public class TestLock {

	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket, "1号窗口").start();
		new Thread(ticket, "2号窗口").start();
		new Thread(ticket, "3号窗口").start();
	}
	
}

class Ticket implements Runnable {

	private int ticket = 100;
	
	private Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		while (true) {
			lock.lock();
			
			try {
				if (ticket > 0) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread().getName() + "：" + --ticket);
				}
			} finally {
				lock.unlock();
			}
		}
	}
	
}
