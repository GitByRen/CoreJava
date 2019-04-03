package com.important.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jdk1.5以后：
 * 同步锁Lock：是一个显示锁，需要通过lock()方法上锁，通过unlock()方法释放锁
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
