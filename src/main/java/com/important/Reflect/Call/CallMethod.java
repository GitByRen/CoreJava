package com.important.Reflect.Call;

import java.lang.reflect.Method;

import org.junit.Test;

import com.important.Reflect.Person;

public class CallMethod {

    /**
     * 调用运行时类中指定的方法
     */
    @Test
    public void test1() throws Exception {
        Class clazz = Person.class;
        Person p = (Person) clazz.newInstance();
        // getMethod(String methodName,Class ... params):获取运行时类中声明为public的方法
        Method method = clazz.getMethod("show");
        // 调用指定的方法：Object invoke(Object obj,Object ... obj)
        Object returnVal = method.invoke(p);
        System.out.println(returnVal);

        Method method2 = clazz.getMethod("toString");
        Object invoke = method2.invoke(p);
        System.out.println(invoke);

        // 静态方法调用
        Method method3 = clazz.getMethod("info");
        method3.invoke(Person.class);

        // getDeclaredMethod(String methodName,Class ... params):获取运行时类中指定的方法
        Method method4 = clazz.getDeclaredMethod("display", String.class, Integer.class);
        method4.setAccessible(true);
        Object invoke2 = method4.invoke(p, "CHINA", 10);
        System.out.println(invoke2);
    }
}
