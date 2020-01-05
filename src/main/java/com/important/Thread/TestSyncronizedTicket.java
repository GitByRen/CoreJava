package com.important.Thread;

/**
 * 如何实现线程安全：
 * 	方式一：同步代码块
 * 	   
 * 	方式二：同步方法
 * 
 * 零长度的byte数组对象创建起来将比任何对象都经济――查看编译后的字节码：生成零长度的byte[]对象只需3行操作码，
 * 而Object lock = new Object()则需要7行操作码。
 * 
 * 无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；
 * 如果synchronized作用的对象是一个静态方法或一个类，则锁住的是当前类的class对象
 * 
 * synchronized底层原理：
 * ，一个是通过方法flags标志，一个是monitorenter和monitorexit指令操作。
 * 
 * 
 */
public class TestSyncronizedTicket {

	public static void main(String[] args) {
		SafeWindow w = new SafeWindow();
//		Window2 w = new Window2();
		Thread t1 = new Thread(w);
		Thread t2 = new Thread(w);
		Thread t3 = new Thread(w);
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
	byte[] o = new byte[0];
	
	@Override
	public void run() {
		while (true) {
			// 同步代码块
			synchronized (this) {
				if (ticket > 0) {
					System.out.println(Thread.currentThread().getName() + ":" + "售票：" + ticket--);
				} else {
					break;
				}
			}
//			synchronized (o) {
//				if (ticket > 0) {
//					System.out.println(Thread.currentThread().getName() + ":" + "售票：" + ticket--);
//				} else {
//					break;
//				}
//			}
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
			System.out.println(this);
			this.show();
		}
	}

	public synchronized void show() {
		if (ticket > 0) {
			System.out.println(Thread.currentThread().getName() + ":售票：" + ticket--);
		}
	}
	
}