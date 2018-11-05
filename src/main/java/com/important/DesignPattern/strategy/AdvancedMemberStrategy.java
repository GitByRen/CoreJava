package com.important.DesignPattern.strategy;

public class AdvancedMemberStrategy implements MemberStrategy {

	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("高级会员的折扣为20%");
		return booksPrice * 0.8;
	}

}
