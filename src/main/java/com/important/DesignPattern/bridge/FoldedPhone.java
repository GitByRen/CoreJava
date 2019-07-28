package com.important.DesignPattern.bridge;

public class FoldedPhone extends Phone {

	public FoldedPhone(Brand brand) {
		super(brand);
	}

	@Override
	public void open() {
		super.open();
		System.out.println("折叠手机open");
	}

	@Override
	public void close() {
		super.close();
		System.out.println("折叠手机close");
	}

	@Override
	public void call() {
		super.call();
		System.out.println("折叠手机call");
	}

}
