package com.important.DesignPattern.strategy;

public class Price {

	// 持有一个具体的策略对象
	private MemberStrategy strategy;
	
	public Price(MemberStrategy strategy) {
		this.strategy = strategy;
	}
	
	// 计算打折后的价格
	public double quote(double booksPrice) {
		return strategy.calcPrice(booksPrice);
	}
	
}
