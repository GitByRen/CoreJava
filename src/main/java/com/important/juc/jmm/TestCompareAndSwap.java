package com.important.juc.jmm;

/**
 * 模拟CAS算法
 * 
 * AtomicInteger.class：
 * public final int getAndIncrement() {
 *    return U.getAndAddInt(this, VALUE, 1);
 * }
 * 
 * Unsafe.class：
 * public final int getAndAddInt(Object o, long offset, int delta) {
 *	    int v;
 *	    do {
 *	        v = getIntVolatile(o, offset);
 *	    } while (!weakCompareAndSetInt(o, offset, v, v + delta));
 *	    return v;
 *	}
 * 
 */
public class TestCompareAndSwap {

	public static void main(String[] args) {
		final CompareAndSwap cas = new CompareAndSwap();

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				int expectedValue = cas.get();
				boolean compareAndSet = cas.compareAndSet(expectedValue, (int) (Math.random()) * 101);
				System.out.println(compareAndSet);
			}).start();
		}
	}

}

class CompareAndSwap {

	private int value;

	// 获取内存值
	public synchronized int get() {
		return value;
	}

	// 比较
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;

		if (oldValue == expectedValue) {
			value = newValue;
		}

		return oldValue;
	}

	// 设置
	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}

}