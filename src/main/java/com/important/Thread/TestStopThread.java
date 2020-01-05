package com.important.Thread;

public class TestStopThread {

	public static void main(String[] args) throws InterruptedException {
		MyThread thread=new MyThread();  
        thread.start();  
        Thread.sleep(3000);  
        thread.interrupt();
	}
	
}

class MyThread extends Thread {
	
	int i = 0;
	
	@Override
	public void run() {
		while (true) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("中断异常被捕获");
				return;
			}
			i++;
		}
	}
	
}