package com.important.DesignPattern.decorate;

// 抽象构件
public abstract class Drink {

	private String des;
	private float price = 0f;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	// 计算费用的抽象方法
	public abstract float cost();
	
}
