package com.important.DesignPattern;

public class TestInterface {

	public static void main(String[] args) {
		Computer com = new Computer();
		// com.work(new Printer());

		USB phone = new USB() {
			@Override
			public void start() {
				System.out.println("手机开始工作！");
			}
			@Override
			public void stop() {
				System.out.println("手机停止工作！");
			}
		};

		com.work(phone);
		
		com.work(new USB() {
			@Override
			public void start() {
				System.out.println("xxx开始工作");
			}
			@Override
			public void stop() {
				System.out.println("xxx停止工作");
			}
		});
		
	}

}

class Computer {
	public void work(USB usb) {
		usb.start();
		usb.stop();
	}
}

interface USB {
	void start();

	void stop();
}

class Printer implements USB {

	@Override
	public void start() {
		System.out.println("开始");
	}

	@Override
	public void stop() {
		System.out.println("结束");
	}

}