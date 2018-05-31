package com.important.Thread.Demo;

public class TestProduceConsume {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Producer p = new Producer(clerk);
		Consumer c = new Consumer(clerk);
		Thread t1 = new Thread(p);
		t1.setName("生产者");
		t1.start();

		Thread t2 = new Thread(c);
		t2.setName("消费者1");
		t2.start();
		
		Thread t3 = new Thread(p);
        t3.setName("生产者2");
        t3.start();
	}

}

class Clerk {
	int product;

	public synchronized void addProduct() {
		if (product >= 5) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			product++;
			System.out.println(Thread.currentThread().getName() + ":生产了第" + product + "个产品！");
			notifyAll();
		}
	}

	public synchronized void consumeProduct() {
		if (product <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(Thread.currentThread().getName() + ":消费了第" + product + "个产品！");
			product--;
			notifyAll();
		}
	}
}

class Producer implements Runnable {
	Clerk clerk;

	public Producer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		System.out.println("生产者开始生产产品!");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clerk.addProduct();
		}
	}
}

class Consumer implements Runnable {
	Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		System.out.println("消费者消费产品");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clerk.consumeProduct();
		}
	}
}