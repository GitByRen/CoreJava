package com.important.juc.ccs;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：闭锁，这个类能够使一个线程等待其他线程完成各自的工作后再执行
 *
 */
public class TestCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch cdl = new CountDownLatch(5);
		LatchDemo ld = new LatchDemo(cdl);

		long start = System.currentTimeMillis();

		for (int i = 0; i < 5; i++) {
			new Thread(ld).start();
		}

		// 主线程的操作在这个方法上阻塞,直到其他线程完成各自的任务
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (end - start));
	}

}

class LatchDemo implements Runnable {

	private CountDownLatch countDownLatch;

	public LatchDemo(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 50; i++) {
				if (i % 4 == 0) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			countDownLatch.countDown();
		}
	}

}