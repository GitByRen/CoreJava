package com.important.DesignPattern.decorate.condiment;

import com.important.DesignPattern.decorate.Decorator;
import com.important.DesignPattern.decorate.Drink;

public class Soy extends Decorator {

	public Soy(Drink obj) {
		super(obj);
		setDes("豆浆");
		setPrice(1.5f);
	}

}
