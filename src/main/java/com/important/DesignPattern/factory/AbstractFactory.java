package com.important.DesignPattern.factory;

/**
 * 抽象工厂：用来创建一组相关或者相互依赖的对象。
 * 
 * 抽象工厂模式与工厂方法模式的最大区别就在于：
 * 工厂方法模式针对的是一个产品等级结构；而抽象工厂模式则需要面对多个产品等级结构。
 * 
 */
public class AbstractFactory {
	public static void main(String[] args) {
		IFactory iFactory = new Factory20();
		iFactory.createProduct2().show2();
		iFactory.createProduct3().show3();
	}
}

// 2节车厢
interface IProduct2 {
	public void show2();
}

// 3节车厢
interface IProduct3 {
	public void show3();
}

// 2.0排量2节
class Product202 implements IProduct2 {
	public void show2() {
		System.out.println("这是2.0排量2节产品");
	}
}

// 2.4排量2节
class Product242 implements IProduct2 {
	public void show2() {
		System.out.println("这是2.4排量2节产品");
	}
}

// 2.0排量3节
class Product203 implements IProduct3 {
	public void show3() {
		System.out.println("这是2.0排量3节产品");
	}
}

// 2.4排量3节
class Product243 implements IProduct3 {
	public void show3() {
		System.out.println("这是2.4排量3节产品");
	}
}

interface IFactory {
	// 2节车厢
	public IProduct2 createProduct2();

	// 3节车厢
	public IProduct3 createProduct3();
}

// 2.0排量工厂
class Factory20 implements IFactory {
	// 2.0排量2节车厢
	public IProduct2 createProduct2() {
		return new Product202();
	}

	// 2.0排量3节车厢
	public IProduct3 createProduct3() {
		return new Product203();
	}
}

// 2.4排量工厂
class Factory24 implements IFactory {
	// 2.4排量2节车厢
	public IProduct2 createProduct2() {
		return new Product242();
	}

	// 2.4排量3节车厢
	public IProduct3 createProduct3() {
		return new Product243();
	}
}