package com.important.juc.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * corePoolSize： 线程池核心线程数
 * maximumPoolSize：线程池最大数
 * keepAliveTime： 空闲线程存活时间，表示线程没有任务执行时最多保持多久时间会终止，然后线程池的数目维持在corePoolSize 大小；
 * unit：时间单位
 * workQueue： 线程池所使用的缓冲队列
 * threadFactory：线程池创建线程使用的工厂
 * handler：线程池对拒绝任务的处理策略
 * 
 * 拒绝策略：
 * AbortPolicy：抛出RejectedExecutionException
 * DiscardPolicy：什么也不做，直接忽略
 * DiscardOldestPolicy：丢弃执行队列中最老的任务，尝试为当前提交的任务腾出位置
 * CallerRunsPolicy：直接由提交任务者执行这个任务
 * 
 */
public class ThreadPoolDetail {

	public static void main(String[] args) {
//		characteristic1();

//		characteristic2();

//		characteristic3();

//		characteristic4();
		
		// 获取CPU核心数
		System.out.println(Runtime.getRuntime().availableProcessors());

		/**
		 * 如何配置最佳线程数？
		 * 
		 *  cpu密集：该任务需要大量的运算，而没有阻塞；公式：cpu核数+1
		 *  io密集：
		 *  ①不是一直在执行任务，应配置尽可能多的线程；公式：cpu核数*2
		 *  ②该任务需要大量的IO，即大量的阻塞，故需要多配置线程数；公式：cpu核数 / (1 - 阻塞系数) 阻塞系数在0.8-0.9之间
		 */
	}

	/**
	 * 特性四：当队列里的任务数达到上限，并且池中正在运行的线程数等于maximumPoolSize，对于新加入的任务，执行拒绝策略（线程池默认的拒绝策略是抛异常）。
	 */
	private static void characteristic4() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

		pool.execute(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----------hello world01-----------");
		});

		pool.execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----------hello world02-----------");
		});

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "----------hello world03-----------");
		});

		pool.execute(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----------hello world04-----------");
		});

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "----------hello world05-----------");
		});

		pool.shutdown();
	}

	/**
	 * 特性三：当队列里的任务数达到上限，并且池中正在运行的线程数小于maximumPoolSize，对于新加入的任务，新建线程。
	 */
	private static void characteristic3() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

		pool.execute(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----------hello world01-----------");
		});

		pool.execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----------hello world02-----------");
		});

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "----------hello world03-----------");
		});

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "----------hello world04-----------");
		});

		pool.shutdown();
	}

	/**
	 * 特性二：当池中正在运行的线程数大于等于corePoolSize时，新插入的任务进入workQueue排队（如果workQueue长度允许），等待空闲线程来执行。
	 */
	private static void characteristic2() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

		pool.execute(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----------hello world01-----------");
		});

		pool.execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "----------hello world02-----------");
		});

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "----------hello world03-----------");
		});

		pool.shutdown();
	}

	/**
	 * 特性一：当池中正在运行的线程数（包括空闲线程）小于corePoolSize时，新建线程执行任务。
	 */
	private static void characteristic1() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "----------hello world01-----------");
		});

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		pool.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "----------hello world02-----------");
		});

		pool.shutdown();
	}

}
