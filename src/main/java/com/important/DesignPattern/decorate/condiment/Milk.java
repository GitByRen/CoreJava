package com.important.DesignPattern.decorate.condiment;

import com.important.DesignPattern.decorate.Decorator;
import com.important.DesignPattern.decorate.Drink;

public class Milk extends Decorator {

	public Milk(Drink obj) {
		super(obj);
		setDes("牛奶");
		setPrice(2.0f);
	}

}
