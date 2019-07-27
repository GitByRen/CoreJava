package com.important.DesignPattern.adapter.interfaceadapter;

public class Client {

	public static void main(String[] args) {
		AbstractAdapter adapter = new AbstractAdapter() {
			// 只需要去覆盖我们需要使用的接口方法
			@Override
			public void m1() {
				System.out.println("覆盖M1");
			}
		};
		adapter.m1();
	}

}
