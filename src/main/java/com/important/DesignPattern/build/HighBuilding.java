package com.important.DesignPattern.build;

public class HighBuilding extends HouseBuilder {

	@Override
	public void buildBasic() {
		System.out.println("高楼房子打地基100米");
	}

	@Override
	public void buildWalls() {
		System.out.println("高楼房子砌墙20cm");
	}

	@Override
	public void roofed() {
		System.out.println("高楼透明屋顶");
	}

}
