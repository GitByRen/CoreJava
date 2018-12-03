package com.important.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.important.java8.lambda.Employee;

public class StreamMapping {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66), 
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33), 
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77), 
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55));

    // 2. 中间操作
    /*
     * 映射 map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test1(){
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        strList.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
        
        System.out.println("***************************");
        
        emps.stream()
            .map(Employee::getName)
            .forEach(System.out::println);
        
//        emps.stream()
//        	.map(new Function<Employee, String>() {
//				@Override
//				public String apply(Employee t) {
//					return t.getName();
//				}
//			}).forEach(System.out::println);
        
        System.out.println("***************************");
        
        // {{a, a, a}, {b, b, b}}
        Stream<Stream<Character>> stream = strList.stream()
                .map(StreamMapping::filterCharacter);
        stream.forEach((sm) -> {
            sm.forEach(System.out::print);
        });
        
        System.out.println("**********flatMap***********");
        
        // {a, a, a, b, b, b}
        Stream<Character> flatMap = strList.stream()
                .flatMap(StreamMapping::filterCharacter);
        flatMap.forEach(System.out::println);
    }
    
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
    
    /**
     * 排序
     * sorted() - 自然排序
     * sorted(Comparator com) - 定制排序
     */
    @Test
    public void test2() {
        List<String> strList = Arrays.asList("ddd", "aaa", "bbb", "ccc", "eee");
        
        strList.stream()
                .sorted()
                .forEach(System.out::println);
        
        System.out.println("---------------------------");
        
        emps.stream().sorted((e1, e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }
}
