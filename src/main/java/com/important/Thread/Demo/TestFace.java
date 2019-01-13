package com.important.Thread.Demo;

/**
 * 交替打印十次ABC
 */
public class TestFace {

	public static void main(String[] args) {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		
		Print p1 = new Print("A", c, a);
		Print p2 = new Print("B", a, b);
		Print p3 = new Print("C", b, c);
		try {
			new Thread(p1).start();
			Thread.sleep(100);
			new Thread(p2).start();
			Thread.sleep(100);
			new Thread(p3).start();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Print implements Runnable {
	int x = 10;

	String name;
	Object prev;
	Object self;

	public Print(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		while (x > 0) {
			// 如果当前线程不是对象锁的持有者，将抛出IllegalMonitorStateException
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					x--;
					// 如果直接notify或者wait()，相当于this调用，而在这里this指的是p1，p2，p3，这三个对象并没有加锁，所以报错
					self.notify();
				}
				try {
					if (x != 0) {
						prev.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}