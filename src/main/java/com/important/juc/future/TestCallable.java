package com.important.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建执行线程的方式三：实现Callable接口
 * 
 * 相较于实现Runnable接口的方式，方法可以有返回值，并且可以抛出异常
 * 
 * 执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果。
 * 
 * FutureTask是Future接口的实现类
 */
public class TestCallable {
	
	public static void main(String[] args) {
		CallableDemo cd = new CallableDemo();
		
		// 1.Callable需要FutureTask实现类的支持
		FutureTask<Integer> result = new FutureTask<>(cd);
		
		new Thread(result).start();
		// 第二个线程不会有结果
		new Thread(result).start();
		
		// 2.接收线程运算后的结果
		try {
			// FutureTask可用于闭锁，此方法会阻塞主线程直到获取‘将来’结果
			Integer sum = result.get();
			System.out.println(sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}

class CallableDemo implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		
		for (int i = 0; i <= 100; i++) {
			sum += i;
		}
		
		return sum;
	}
	
}