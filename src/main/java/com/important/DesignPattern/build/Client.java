package com.important.DesignPattern.build;

/**
 * 1、将一个复杂对象分解成多个相对简单的部分，然后根据不同需要分别创建它们，最后构建成该复杂对象。
 * 
 * 2、建造者（Builder）模式的定义：指将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，
 * 这样的设计模式被称为建造者模式。它是将一个复杂的对象分解为多个简单的对象，然后一步一步构建而成。
 * 它将变与不变相分离，即产品的组成部分是不变的，但每一部分是可以灵活选择的。
 */
public class Client {

	public static void main(String[] args) {
		CommonHouse commonHouse = new CommonHouse();
		HouseDirector houseDirector = new HouseDirector(commonHouse);
		// 完成盖房子，返回产品
		houseDirector.construct();
	}

}
