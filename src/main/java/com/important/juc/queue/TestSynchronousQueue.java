package com.important.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue 是一个特殊的队列，它的内部同时只能够容纳单个元素。如果该队列已有一元素的话，
 * 试图向队列中插入一个新元素的线程将会阻塞，直到另一个线程将该元素从队列中抽走。同样，如果该队列为空，
 * 试图向队列中抽取一个元素的线程将会阻塞，直到另一个线程向队列中插入了一条新的元素。
 *
 */
public class TestSynchronousQueue {

	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

		new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + "\t put 1");
				blockingQueue.put("1");

				System.out.println(Thread.currentThread().getName() + "\t put 2");
				blockingQueue.put("2");

				System.out.println(Thread.currentThread().getName() + "\t put 3");
				blockingQueue.put("3");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "AAA").start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

				TimeUnit.SECONDS.sleep(5);
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

				TimeUnit.SECONDS.sleep(5);
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "BBB").start();
	}

}
