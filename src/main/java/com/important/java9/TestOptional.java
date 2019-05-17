package com.important.java9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class TestOptional {

	@Test
	public void test1() {
		List<String> list = new ArrayList<>() {

			private static final long serialVersionUID = 1L;

			{
				add("Tom");
				add("Jerry");
				add("Tim");
			}
			
		};

		Optional<List<String>> optional = Optional.ofNullable(list);
		Stream<String> flatMap = optional.stream().flatMap(x -> x.stream());
		flatMap.forEach(System.out::println);
	}
	
	/**
	 * Optional::stream
	 */
	@Test
	public void test2() {
		List<Optional<String>> list = Arrays.asList(Optional.empty(), Optional.of("A"), Optional.empty(),
				Optional.of("B"));
		List<String> collect = list.stream().flatMap(Optional::stream).collect(Collectors.toList());
		System.out.println(collect);
	}

	
	@Test
	public void test3() {
		Optional<Integer> optional = Optional.ofNullable(null);
		optional.ifPresentOrElse(x -> System.out.println("Value: " + x), () -> System.out.println("Not Present."));
	}
}
