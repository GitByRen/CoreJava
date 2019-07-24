package com.important.DesignPattern.build;

// Abstract Builder
public abstract class HouseBuilder {

	protected House house = new House();

	abstract void buildBasic();

	abstract void buildWalls();

	abstract void roofed();

	// 将产品返回
	public House buildHouse() {
		return house;
	}

}
