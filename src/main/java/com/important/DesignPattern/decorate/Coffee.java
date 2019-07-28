package com.important.DesignPattern.decorate;

public class Coffee extends Drink {

	@Override
	public float cost() {
		return super.getPrice();
	}

}
