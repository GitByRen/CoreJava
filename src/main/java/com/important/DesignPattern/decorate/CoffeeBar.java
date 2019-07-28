package com.important.DesignPattern.decorate;

import com.important.DesignPattern.decorate.condiment.Chocolate;
import com.important.DesignPattern.decorate.condiment.Milk;
import com.important.DesignPattern.decorate.drink.LongBlack;

/**
 * 1、定义：在不改变现有对象结构的情况下，动态地给该对象增加一些职责（即增加其额外功能）的模式，它属于对象结构型模式。
 * 
 * 2、应用场景：
 *  1)当需要给一个现有类添加附加职责，而又不能采用生成子类的方法进行扩充时。例如，该类被隐藏或者该类是终极类或者采用继承方式会产生大量的子类。
 *  2)当需要通过对现有的一组基本功能进行排列组合而产生非常多的功能时，采用继承关系很难实现，而采用装饰模式却很好实现。
 *  3)当对象的功能要求可以动态地添加，也可以再动态地撤销时。
 */
public class CoffeeBar {

	public static void main(String[] args) {
		// 2份巧克力+1份牛奶的LongBlack

		// 单点一份LongBlack
		Drink order = new LongBlack();
		System.out.println("描述：" + order.getDes());
		System.out.println("费用：" + order.cost());

		// 加入一份牛奶
		order = new Milk(order);
		System.out.println("加入一份牛奶描述：" + order.getDes());
		System.out.println("加入一份牛奶费用：" + order.cost());

		// 加入一份巧克力
		order = new Chocolate(order);
		System.out.println("加入一份牛奶，加入一份巧克力描述：" + order.getDes());
		System.out.println("加入一份牛奶，加入一份巧克力费用：" + order.cost());
	}

}
