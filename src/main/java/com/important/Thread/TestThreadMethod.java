package com.important.Thread;

/**
 * 1.start()：启动线程并执行相应的run方法
 * 2.run()：子线程要执行的代码
 * 3.currentThread()：静态的，调取当前线程
 * 4.getName()：获取此线程的名字
 * 5.setName()：设置此线程的名字
 * 6.yield()：调用此方法的线程释放当前CPU的执行权，实际中无法保证yield()达到让步目的
 * 7.join()：在A线程中调用B线程的join()方法，A线程停止，直到B线程执行完毕
 * 8.isAlive()：判断当前线程是否存活
 * 9.sleep(long l)：显示的让当前线程睡眠l毫秒
 * 10.线程通信：wait() notify() notifyAll()
 * 11.setPriority(int newPriority)：改变线程的优先级
 *    getPriority()：返回线程优先级
 * 
 * sleep 方法允许较低优先级的线程获得运行机会，但 yield()方法执行时，
 * 当前线程仍处在可运行状态，所以，不可能让出较低优先级的线程些时获得 CPU 占有权。
 */
public class TestThreadMethod {
	public static void main(String[] args) {
		SubThread1 st = new SubThread1();
		st.setName("子线程1：");
		st.setPriority(Thread.MAX_PRIORITY);
		st.start();
		Thread.currentThread().setName("主线程：");
		for (int i = 1; i <= 100; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
//			if (i % 10 == 0) {
//				Thread.currentThread().yield();
//			}
			
//			if(i == 20) {
//				try {
//					st.join();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
		System.out.println(st.isAlive());
	}
}


//1.创建一个继承于Thread的子类
class SubThread1 extends Thread {
	// 2.重写run方法
	public void run() {
		for (int i = 1; i <= 100; i++) {
//			try {
//				// 因为重写了Thread的run方法，而Thread的run方法没有抛出异常，所以该方法也不能抛出异常，所以sleep只能捕获异常
//				Thread.currentThread().sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
		}
	}
}
