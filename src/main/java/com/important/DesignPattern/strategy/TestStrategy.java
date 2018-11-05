package com.important.DesignPattern.strategy;

/**
 * 策略模式：
 * 	策略模式属于对象的行为模式。其用意是针对一组算法，将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。
 *  策略模式使得算法可以在不影响到客户端的情况下发生变化。
 *
 *①优点：使用策略模式可以避免使用多重条件(if-else)语句。
 *②缺点：由于策略模式把每个具体的策略实现都单独封装成为类，如果备选的策略很多的话，那么对象的数目就会很可观。
 */
public class TestStrategy {

	public static void main(String[] args) {
		AdvancedMemberStrategy advanced = new AdvancedMemberStrategy();
		Price price = new Price(advanced);
		double quote = price.quote(100);
		System.out.println("价格" + quote);
	}

}
