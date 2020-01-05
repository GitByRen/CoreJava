package com.important.juc.ccs;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	
	private static Semaphore semaphore1 = new Semaphore(1);
	private static Semaphore semaphore2 = new Semaphore(1);

	public static void main(String[] args) {
		final Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 在这睡上三秒，结果就不一样了
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("产品经理规划新需求");
				semaphore1.release();
			}
		});

		final Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("thread2 start");
					semaphore1.acquire();
					System.out.println("开发人员开发新需求功能");
					semaphore2.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("thread3 start");
					semaphore2.acquire();
					System.out.println("thread2.join();");
					thread2.join();
					semaphore2.release();
					System.out.println("测试人员测试新功能");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		System.out.println("早上：");
		System.out.println("测试人员来上班了...");
		thread3.start();
		System.out.println("产品经理来上班了...");
		System.out.println("开发人员来上班了...");
		thread2.start();
		thread1.start();
	}
}