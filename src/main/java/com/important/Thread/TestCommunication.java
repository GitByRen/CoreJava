package com.important.Thread;

/**
 * 无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；
 * 如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁。
 */
public class TestCommunication {

	public static void main(String[] args) {
		// 交替打印1-100
		PrintNum pn = new PrintNum();
		new Thread(pn).start();
		new Thread(pn).start();
	}

}

class PrintNum implements Runnable {
	int num = 1;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				notify();
				if (num <= 100) {
					System.out.println(Thread.currentThread().getName() + ":" + num);
					num++;
				} else {
					break;
				}
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
