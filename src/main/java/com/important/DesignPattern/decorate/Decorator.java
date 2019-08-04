package com.important.DesignPattern.decorate;

// 抽象装饰
public class Decorator extends Drink {

	private Drink obj;

	public Decorator(Drink obj) {
		this.obj = obj;
	}

	@Override
	public float cost() {
		return super.getPrice() + obj.cost();
	}

	@Override
	public String getDes() {
		return super.getDes() + " " + super.getPrice() + "&&" + obj.getDes();
	}

}
