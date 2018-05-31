package com.important.Common;

class Root {
	static {
		System.out.println("Root的静态初始化块");
	}

	{
		System.out.println("Root的非静态初始化块");
	}

	public Root() {
		System.out.println("Root的构造器");
	}
}

class Mid extends Root {
	static {
		System.out.println("Mid的静态初始化块");
	}

	{
		System.out.println("Mid的非静态初始化块");
	}

	public Mid(String msg) {
		System.out.println("msg：" + msg);
	}
}

class Leaf extends Mid {
	static {
		System.out.println("Leaf的静态初始化块");
	}

	{
		System.out.println("Leaf的非静态初始化块");
	}

	public Leaf() {
		super("尚硅谷");
		System.out.println("Leaf的构造器");
	}

}

public class TestLeaf {
	public static void main(String[] args) {
		new Leaf();
		// 每实例化一次对象，非静态代码块就执行一次
		new Leaf();
	}
}
