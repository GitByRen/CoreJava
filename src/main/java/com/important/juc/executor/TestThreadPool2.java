package com.important.juc.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadPool2 {

	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		List<Future<Integer>> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Future<Integer> future = fixedThreadPool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int sum = 0;

					for (int i = 0; i <= 100; i++) {
						sum += i;
					}

					return sum;
				}
			});
			
			list.add(future);
		}
		
		fixedThreadPool.shutdown();
		
		for (Future<Integer> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

}
