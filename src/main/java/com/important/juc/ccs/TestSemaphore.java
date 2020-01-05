package com.important.juc.ccs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore信号量被用于控制特定资源在同一时间被访问的个数
 */
public class TestSemaphore {

	public static void main(String[] args) {
		// 可伸缩
		Semaphore semaphore = new Semaphore(3);
		
		// 6部车抢3个车位
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + "进来了");
					// 从此信号量获取一个许可
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "抢到车位");
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName() + "停车3秒后离开车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// 释放一个许可
					semaphore.release();
				}
				
				System.out.println("获取当前可用的许可证数量：release：" + semaphore.availablePermits());
				
			}, String.valueOf(i)).start();
		}
		
	}
	
}
