package com.important.DesignPattern.strategy;

public interface MemberStrategy {

	/**
	 * 计算图书价格
	 * 
	 * @param booksPrice 图书原价
	 * @return
	 */
	public double calcPrice(double booksPrice);
	
}
