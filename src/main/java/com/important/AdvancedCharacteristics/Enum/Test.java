package com.important.AdvancedCharacteristics.Enum;

public class Test {
	public static void main(String[] args) {
		Season spring = Season.SPRING;
		System.out.println(spring);
		Seasons[] values = Seasons.values();
		for (Seasons seasons : values) {
			System.out.println(seasons);
		}
	}
}
