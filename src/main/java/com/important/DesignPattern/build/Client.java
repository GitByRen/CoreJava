package com.important.DesignPattern.build;

/**
 * 1、在软件开发过程中有时需要创建一个复杂的对象，这个复杂对象通常由多个子部件按一定的步骤组合而成。
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
