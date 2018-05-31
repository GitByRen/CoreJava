package com.important.Thread;

/**
 * 如何实现线程安全：
 * 	方式一：同步代码块
 * 	   
 * 	方式二：同步方法
 */
public class TestSyncronizedTicket {

	public static void main(String[] args) {
//		SafeWindow w = new SafeWindow();
		Window2 w2 = new Window2();
		Thread t1 = new Thread(w2);
		Thread t2 = new Thread(w2);
		Thread t3 = new Thread(w2);
		t1.setName("窗口1：");
		t2.setName("窗口2：");
		t3.setName("窗口3：");
		t1.start();
		t2.start();
		t3.start();
	}

}

class SafeWindow implements Runnable {
	int ticket = 100;
	// 任何一个类的对象都可以充当锁的对象
	Object o = new Object();
	
	@Override
	public void run() {
		while (true) {
			// 同步代码块
//			synchronized (this) {
//				if (ticket > 0) {
//					System.out.println(Thread.currentThread().getName() + ":" + "售票：" + ticket--);
//				} else {
//					break;
//				}
//			}
			synchronized (o) {
				if (ticket > 0) {
					System.out.println(Thread.currentThread().getName() + ":" + "售票：" + ticket--);
				} else {
					break;
				}
			}
		}
	}
}


class Window2 implements Runnable {
	int ticket = 100;

	@Override
	public void run() {
		while (true) {
			if(ticket == 0) {
				break;
			}
			show();
		}
	}

	public synchronized void show() {
		if (ticket > 0) {
			System.out.println(Thread.currentThread().getName() + ":售票：" + ticket--);
		}
	}
	
}