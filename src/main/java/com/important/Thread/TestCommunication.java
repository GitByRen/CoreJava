package com.important.Thread;

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