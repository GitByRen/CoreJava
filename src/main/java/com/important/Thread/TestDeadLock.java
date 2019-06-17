package com.important.Thread;

/**
 * jps -l：查看进程号
 * jstack 进程号：查看
 */
public class TestDeadLock {
	
	static StringBuilder sb1 = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	
	public static void main(String[] args) {
		new Thread(() -> {
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
		}).start();
		
		new Thread(() -> {
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
		}).start();
	}
}
