package com.important.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1、当阻塞队列是空时，从队列中获取元素的操作将会被阻塞 
 * 2、当阻塞队列是满时，往队列里添加元素的操作将会被阻塞
 * 
 * BlockingQueue： 
 * ArrayBlockingQueue 
 * LinkedBlockingQueue 
 * SynchronousQueue
 */
public class TestBlockingQueue {

	public static void main(String[] args) throws Exception {
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
		
//		addAndRemove(blockingQueue);

//		offerAndPull(blockingQueue);

//		putAndTake(blockingQueue);
		
		offerAndPullTimeout(blockingQueue);
		
	}

	/**
	 * 限时阻塞调用线程，直到超时或线程被中断或队列状态可用
	 */
	private static void offerAndPullTimeout(BlockingQueue<String> blockingQueue) throws InterruptedException {
		System.out.println(blockingQueue.offer("a", 1, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("b", 1, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("c", 1, TimeUnit.SECONDS));
		// 超时则中断阻塞
		System.out.println(blockingQueue.offer("d", 1, TimeUnit.SECONDS));
	}

	/**
	 * 阻塞调用线程，直到线程被中断或队列状态可用
	 */
	private static void putAndTake(BlockingQueue<String> blockingQueue) throws InterruptedException {
		blockingQueue.put("a");
		blockingQueue.put("b");
		blockingQueue.put("c");
		System.out.println("=================");

//		blockingQueue.put("x");

		blockingQueue.take();
		blockingQueue.take();
		blockingQueue.take();
	}

	/**
	 * 返回true、false、null
	 */
	private static void offerAndPull(BlockingQueue<String> blockingQueue) {
		System.out.println(blockingQueue.offer("a"));
		System.out.println(blockingQueue.offer("b"));
		System.out.println(blockingQueue.offer("c"));

//		System.out.println(blockingQueue.offer("x"));

		// 返回队首元素，如果队列为空则返回null
		System.out.println(blockingQueue.peek());

		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());

		// 如果队列为空返回null
		System.out.println(blockingQueue.poll());
	}

	/**
	 * 抛出异常
	 */
	private static void addAndRemove(BlockingQueue<String> blockingQueue) {
		System.out.println(blockingQueue.add("a"));
		System.out.println(blockingQueue.add("b"));
		System.out.println(blockingQueue.add("c"));

//		System.out.println(blockingQueue.add("x"));
		// 返回队首元素，如果队列为空则抛出异常
		System.out.println(blockingQueue.element());

		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
	}

}
