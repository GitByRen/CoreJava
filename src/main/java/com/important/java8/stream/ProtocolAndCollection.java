package com.important.java8.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.Test;

import com.important.java8.lambda.Employee;
import com.important.java8.lambda.Employee.Status;

/**
 * 终止操作
 */
public class ProtocolAndCollection {

    List<Employee> emps = Arrays.asList(
        new Employee(102, "李四", 59, 6666.66, Status.BUSY),
        new Employee(101, "张三", 18, 9999.99, Status.FREE),
        new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
        new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
        new Employee(104, "赵六", 8, 7777.77, Status.FREE),
        new Employee(104, "赵六", 8, 7777.77, Status.FREE),
        new Employee(105, "田七", 38, 5555.55, Status.BUSY));

    /*
     * 收集
     * collect---将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test2() {
        List<String> list = emps.stream()
            .map(Employee::getName)
            .collect(Collectors.toList());
        System.out.println(list);
        
        System.out.println("**************************");
        
        HashSet<String> collect = emps.stream()
            .map(Employee::getName)
            .collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect);
    }
    
    @Test
    public void test3() {
        // 总数
        Long count = emps.stream()
            .map(Employee::getName)
            .collect(Collectors.counting());
        System.out.println(count);
        
        System.out.println("**************************");
        
        // 平均值
        Double average = emps.stream()
            .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);
        
        // 最大值
        Optional<Employee> max = emps.stream()
            .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());
        
        // 最小工资
        Optional<Double> min = emps.stream()
            .map(Employee::getSalary)
            .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }
    
    // 分组
    @Test
    public void test4() {
        Map<Status, List<Employee>> map = emps.stream()
            .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
        
        // 多级分组(先按status分组，再按年龄分组)
        Map<Status, Map<String, List<Employee>>> dMap = 
                emps.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
            if (((Employee) e).getAge() <= 35) {
                return "青年";
            } else if (((Employee) e).getAge() <= 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        System.out.println(dMap);
    }
    
    @Test
    public void test5() {
        // summarizing
        DoubleSummaryStatistics dss = emps.stream()
            .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getSum());
        System.out.println(dss.getMax());
        
        // joining
        String collect = emps.stream()
            .map(Employee::getName)
            .collect(Collectors.joining(","));
        System.out.println(collect);
    }
    
    
    /*
     * 归约 reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 
     * ——可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
            .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        
        System.out.println("***************************");
        
        // 计算工资的总和
        Optional<Double> reduce = emps.stream()
            .map(Employee::getSalary)
            .reduce(Double::sum);
        System.out.println(reduce.get());
        
        // LongStream
        long longSum1 = LongStream.rangeClosed(0L, 100000000L)
                .parallel()
                .reduce(0L, Long::sum);
        long longSum2 = LongStream.rangeClosed(0, 100000000).parallel().sum();
        System.out.println(longSum1);
        System.out.println(longSum2);
    }
    
    @Test
    public void testParallelStream() {
    	List<Double> empList = emps.parallelStream().map(Employee::getPrice).collect(Collectors.toList());
    	System.out.println(empList);
    	
    	// 可以指定executor线程池
    	List<CompletableFuture<Double>> empListCF = emps.stream().map(emp -> CompletableFuture.supplyAsync(emp::getPrice)).collect(Collectors.toList());
        List<Double> empLists = empListCF.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(empLists);
    }

}
