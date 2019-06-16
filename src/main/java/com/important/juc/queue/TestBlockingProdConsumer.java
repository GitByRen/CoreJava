package com.important.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestBlockingProdConsumer {

	public static void main(String[] args) {
		BlockingProdConsumer block = new BlockingProdConsumer(new ArrayBlockingQueue<>(10));

		new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					block.consume();
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "Consumer1：").start();

		new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					block.prod();
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "Prod：").start();

		new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					block.consume();
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "Consumer2：").start();
	}

}

class BlockingProdConsumer {

	private AtomicInteger atomicInteger = new AtomicInteger();
	private BlockingQueue<Integer> blockingQueue;

	public BlockingProdConsumer(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void prod() throws Exception {
		int incrementAndGet = atomicInteger.incrementAndGet();
		boolean resValue = blockingQueue.offer(incrementAndGet, 2, TimeUnit.SECONDS);
		if (resValue) {
			System.out.println(Thread.currentThread().getName() + "\t 插入队列" + incrementAndGet + "成功");
		} else {
			System.out.println(Thread.currentThread().getName() + "\t 插入队列" + incrementAndGet + "失败");
		}
	}

	public void consume() throws Exception {
		Integer poll = blockingQueue.poll(2, TimeUnit.SECONDS);
		if (null == poll) {
			System.out.println(Thread.currentThread().getName() + "等待中...");
			return;
		}
		System.out.println(Thread.currentThread().getName() + "消费：\t" + poll);
	}

}