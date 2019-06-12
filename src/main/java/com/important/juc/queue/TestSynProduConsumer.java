package com.important.juc.queue;

public class TestSynProduConsumer {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		Productor productor = new Productor(clerk);
		Consumer consumer = new Consumer(clerk);
		
		new Thread(productor, "生产者1").start();
		new Thread(consumer, "消费者1").start();
		new Thread(productor, "生产者2").start();
		new Thread(consumer, "消费者2").start();
	}
	
}

class Clerk {

	private int product = 0;

	public synchronized void addProduct() {
		while (product >= 1) {
			System.out.println("产品已满!");
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println(Thread.currentThread().getName() + ":" + ++product);
		notifyAll();
	}

	public synchronized void consumeProduct() {
		// 使用if有虚假唤醒问题
		while (product <= 0) {
			System.out.println("缺货！");
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 

		System.out.println(Thread.currentThread().getName() + ":" + product--);
		notifyAll();
	}

}


class Productor implements Runnable {

	private Clerk clerk;
	
	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			clerk.addProduct();
		}
	}
	
}

class Consumer implements Runnable {

	private Clerk clerk;
	
	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			clerk.consumeProduct();
		}
	}
	
}