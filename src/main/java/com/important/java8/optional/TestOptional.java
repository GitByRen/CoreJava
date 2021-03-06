package com.important.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.important.java8.lambda.Employee;

public class TestOptional {

    /*
     * 一、Optional 容器类：用于尽量避免空指针异常
     *  Optional.of(T t) : 创建一个 Optional 实例
     *  Optional.empty() : 创建一个空的 Optional 实例
     *  Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     *  isPresent() : 判断是否包含值
     *  orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     *  orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     *  map(Function f): 如果有值则对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     *  flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */
	@Test
	public void test5() {
		Employee employee = new Employee("张三", 18);
		Optional<Employee> employeeOpt = Optional.ofNullable(employee);
		employeeOpt.ifPresent(e -> {
			// ...
		});
		
		employeeOpt.orElseGet(() -> {
			return null;
		});
	}
	
	@Test
	public void test4() {
		Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18));
		Optional<String> map = op.map((e) -> e.getName());
		System.out.println(map.get());
		
		Optional<String> flatMap = op.flatMap((e) -> Optional.of(e.getName()));
		System.out.println(flatMap);
	}
	
    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(new Employee());
        // 有值就获取，没值就什么都不做
        if(op.isPresent()) {
            System.out.println(op.get());
        }
        
        Optional<Employee> op1 = Optional.ofNullable(null);
        Employee orElse = op1.orElse(new Employee("张三", 18));
        System.out.println(orElse);
    }
    
    
    @Test
    public void test2() {
        // 构建空的optional
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty.get());
        
        // of和empty的综合
        Optional<Employee> ofNullable = Optional.ofNullable(new Employee());
        System.out.println(ofNullable.get());
    }
    
    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());
    }
    
}
