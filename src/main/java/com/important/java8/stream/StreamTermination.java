package com.important.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.important.java8.lambda.Employee;
import com.important.java8.lambda.Employee.Status;

/**
 * 终止操作
 */
public class StreamTermination {

    List<Employee> emps = Arrays.asList(
               new Employee(102, "李四", 59, 6666.66, Status.BUSY),
               new Employee(101, "张三", 18, 9999.99, Status.FREE),
               new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
               new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
               new Employee(104, "赵六", 8, 7777.77, Status.FREE),
               new Employee(104, "赵六", 8, 7777.77, Status.FREE),
               new Employee(105, "田七", 38, 5555.55, Status.BUSY));

    /*
     *  查找与匹配
        allMatch——检查是否匹配所有元素
        anyMatch——检查是否至少匹配一个元素
        noneMatch——检查是否没有匹配的元素
        findFirst——返回第一个元素
        findAny——返回当前流中的任意元素
     */
    
    @Test
    public void test1() {
        boolean allMatch = emps.stream()
            .allMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(allMatch);
        
        boolean anyMatch = emps.stream()
            .anyMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(anyMatch);
        
        boolean noneMatch = emps.stream()
            .noneMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(noneMatch);
        
        // 如果返回值有可能为空的话，就封装到Optional中去
        Optional<Employee> findFirst = emps.stream()
            .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
            .findFirst();
        System.out.println(findFirst.get());
        
        Optional<Employee> findAny = emps.stream()
            .filter((e) -> e.getStatus().equals(Status.FREE))
            .findAny();
        System.out.println(findAny.get());
    }
    
    /*
        count——返回流中元素的总个数
        max——返回流中最大值
        min——返回流中最小值
     */
    @Test
    public void test2() {
        long count = emps.stream()
            .count();
        System.out.println(count);
        
        // 最高工资的人
        Optional<Employee> max = emps.stream()
            .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());
        
        // 最小工资是多少
        Optional<Double> min = emps.stream()
            .map(Employee::getSalary)
            .min(Double::compare);
        System.out.println(min.get());
    }

}
