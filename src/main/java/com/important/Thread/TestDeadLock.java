package com.important.Thread;

public class TestDeadLock {
	
	static StringBuilder sb1 = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				synchronized (sb1) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sb1.append("A");
					synchronized (sb2) {
						sb2.append("B");
						System.out.println(sb1);
						System.out.println(sb2);
					}
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				synchronized (sb2) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sb1.append("A");
					synchronized (sb1) {
						sb2.append("B");
						System.out.println(sb1);
						System.out.println(sb2);
					}
				}
			}
		}.start();
	}
}
