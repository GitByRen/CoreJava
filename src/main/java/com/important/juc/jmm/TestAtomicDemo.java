package com.important.juc.jmm;

/**
 * 什么是原子性：即某个操作cpu不可以在中途暂停然后再调度
 * 
 * 1.i++的原子性问题 int i = 10; i = i++; // 10
 * 
 * 以上可以拆解成三步：读->改->写 
 * 
 * int temp = i;
 * i = i + 1;
 * i = temp;
 * 
 * 这三步的jvm指令为：
 * mov    0xc(%r10),%r8d ; Load
 * inc    %r8d           ; Increment
 * mov    %r8d,0xc(%r10) ; Store
 * lock addl $0x0,(%rsp) ; StoreLoad Barrier
 * 注意最后一步是内存屏障。
 * 
 * 从Load到Store到内存屏障，一共4步，其中最后一步jvm让这个最新的变量的值在所有线程可见，也就是最后一步
 * 让所有的CPU内核都获得了最新的值，但中间的几步（从Load到Store）是不安全的，中间如果其他的CPU修改了值将会丢失。
 * 
 */
public class TestAtomicDemo {

	public static void main(String[] args) {
		AtomicDemo atom = new AtomicDemo();

		for (int i = 0; i < 10; i++) {
			new Thread(atom).start();
		}
	}
}

class AtomicDemo implements Runnable {

	private int serialNumber = 0;
//	private AtomicInteger serialNumber = new AtomicInteger();

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "-before:" + serialNumber);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + "：" + getSerialNumber());
	}

	public int getSerialNumber() {
		return serialNumber++;
//		return serialNumber.getAndIncrement();
	}
}