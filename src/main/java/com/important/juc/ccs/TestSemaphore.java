package com.important.juc.ccs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {

	public static void main(String[] args) {
		// 可伸缩
		Semaphore semaphore = new Semaphore(3);
		
		// 6部车抢3个车位
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + "进来了");
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "抢到车位");
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName() + "停车3秒后离开车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
				
				System.out.println("获取当前可用的许可证数量：release：" + semaphore.availablePermits());
				
			}, String.valueOf(i)).start();
		}
		
	}
	
}
