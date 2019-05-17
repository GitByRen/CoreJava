package com.important.java9;

import java.util.HashSet;
import java.util.Set;

public class DiamondOperatorTest {
	
	public static void main(String[] args) {
		diamondOperator();
	}
	
	public static void diamondOperator() {
		Set<String> set = new HashSet<>() {

			private static final long serialVersionUID = 1L;
			
		};
		set.add("MM");
		set.add("JJ");
		set.add("GG");
		set.stream().forEach(System.out::println);
	}
	
}
