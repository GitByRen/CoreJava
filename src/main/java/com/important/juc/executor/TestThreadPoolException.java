package com.important.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadPoolException {

	public static void main(String[] args) {
		catchException1();
		
		catchException2();
		
		// 3、重写ThreadPoolExecutor.afterExecute方法，处理传递的异常引用
	}

	private static void catchException2() {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			Future<?> future = threadPool.submit(() -> {
				System.out.println("current thread name" + Thread.currentThread().getName());
				System.out.print("result## " + 1 / 0);
			});
			// 2、通过Future对象的get方法接收抛出的异常，再处理
			try {
				future.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void catchException1() {
		// 1、try...catch捕获
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			threadPool.submit(() -> {
				System.out.println("current thread name" + Thread.currentThread().getName());
				try {
					System.out.print("result## " + 1 / 0);
				} catch (Exception e) {
					System.out.println("程序出异常了" + e.getMessage());
				}
			});
		}
	}

}
