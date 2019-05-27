package com.important.juc.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

	public static void main(String[] args) {
		atomicReference();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("***************以下是ABA问题的解决**************");

		AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "第一次版本号：" + atomicStampedReference.getStamp());
			// 暂停1秒钟，让t4线程获得和t3线程一样的版本号
			try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {	e.printStackTrace(); }
			atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(),
					atomicStampedReference.getStamp() + 1);
			System.out.println(Thread.currentThread().getName() + "第二次版本号：" + atomicStampedReference.getStamp());
			atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(),
					atomicStampedReference.getStamp() + 1);
			System.out.println(Thread.currentThread().getName() + "第三次版本号：" + atomicStampedReference.getStamp());
		}, "t3").start();

		new Thread(() -> {
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamp);
			// 暂停3秒钟，让t3线程执行
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
			System.out.println(Thread.currentThread().getName() + "修改成功否：" + result);
			System.out.println("当前实际版本号：" + atomicStampedReference.getStamp());
			System.out.println(Thread.currentThread().getName() + "当前实际最新值：" + atomicStampedReference.getReference());
		}, "t4").start();

	}

	private static void atomicReference() {
		// 可以是其他实体类
		AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

		new Thread(() -> {
			atomicReference.compareAndSet(100, 101);
			atomicReference.compareAndSet(101, 100);
		}, "t1").start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());

		}, "t2").start();
	}

}
