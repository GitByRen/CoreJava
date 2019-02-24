package com.important.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.important.java8.lambda.Employee;
import com.important.java8.lambda.Employee.Status;

public class ExerciseStream {

	List<Employee> emps = Arrays.asList(
	        new Employee(102, "李四", 59, 6666.66, Status.BUSY),
	        new Employee(101, "张三", 18, 9999.99, Status.FREE),
	        new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
	        new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
	        new Employee(104, "赵六", 8, 7777.77, Status.FREE),
	        new Employee(104, "赵六", 8, 7777.77, Status.FREE),
	        new Employee(105, "田七", 38, 5555.55, Status.BUSY));
	
	
	@Test
	public void test1() {
		Optional<Integer> sumAll = emps.stream().map(e -> 1).reduce(Integer::sum);
		System.out.println(sumAll.get());
		
		long count = emps.stream().count();
		System.out.println(count);
	}
	
	@Test
	public void test2() {
		Optional<Integer> sumAge = emps.stream()
			.map(Employee::getAge)
			.reduce(Integer::sum);
		System.out.println(sumAge.get());
	}
	
}
