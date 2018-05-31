package com.important.Common;

import java.util.ArrayList;
import java.util.List;

public class TestAutoBoxing {

	/*
	 * *********************自动装箱*********************
	 * 1.Converting a primitive value (an int, for example) into an object of the
	 * corresponding wrapper class (Integer) is called autoboxing. The Java compiler
	 * applies autoboxing when a primitive value is: 
	 * 1).Passed as a parameter to a
	 * method that expects an object of the corresponding wrapper class. 
	 * 作为参数传递给期望对应包装类的对象的方法。
	 * 2).Assigned to a variable of the corresponding wrapper class.
	 * 分配给相应包装器类的变量。
	 * **********************自动拆箱********************
	 * 3).Passed as a parameter to a method that expects a value of the corresponding primitive type.
	 * 作为一个参数传递给一个方法，该方法期望对应的原始类型的值。
	 * 4).Assigned to a variable of the corresponding primitive type.
	 * 分配给对应的原始类型的变量。
	 */
	public static void main(String[] args) {
		Integer i = new Integer(-8);

        // 1. Unboxing through method invocation
        int absVal = absoluteValue(i);
        System.out.println("absolute value of " + i + " = " + absVal);

        List<Double> ld = new ArrayList<>();
        ld.add(3.1416);    // II is autoboxed through method invocation.

        // 2. Unboxing through assignment
        double pi = ld.get(0);
        System.out.println("pi = " + pi);
	}

	public static int absoluteValue(int i) {
        return (i < 0) ? -i : i;
    }
}
