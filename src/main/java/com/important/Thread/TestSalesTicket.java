package com.important.Thread;

public class TestSalesTicket {

	public static void main(String[] args) {
		/*Window window1 = new Window();
		Window window2 = new Window();
		Window window3 = new Window();
		
		window1.setName("窗口1");
		window2.setName("窗口2");
		window3.setName("窗口3");
		
		window1.start();
		window2.start();
		window3.start();*/
		
		//只new了一个对象，所以只有100张票
		Window1 w = new Window1();
		for (int i = 0; i < 3; i++) {
			new Thread(w).start();
		}
	}

}

class Window extends Thread {
	// 共用一个变量
	static int ticket = 100;

	public void run() {
		while (true) {
			if (ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "售票：" + ticket--);
			} else {
				break;
			}
		}
	}
}

class Window1 implements Runnable {
	
	int ticket = 100;
	
	@Override
	public void run() {
		while (true) {
			if (ticket > 0) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "售票：" + ticket--);
			} else {
				break;
			}
		}
	}
}
