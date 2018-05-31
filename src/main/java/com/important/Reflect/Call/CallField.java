package com.important.Reflect.Call;

import java.lang.reflect.Field;

import org.junit.Test;

import com.important.Reflect.Person;

public class CallField {

    @Test
    public void test1() throws Exception {
        Class clazz = Person.class;
        // 1.获取运行时类中声明为public的属性
        Field name = clazz.getField("name");
        // 2.创建运行时类的对象
        Person p = (Person) clazz.newInstance();
        // 3.将运行时类的指定属性赋值
        name.set(p, "Jerry");
        System.out.println(p);

        // 4.getDeclaredField获取运行时类中指定的名为fieldName的属性
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(p, 20);
        System.out.println(p);
    }
}
