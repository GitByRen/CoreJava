package com.important.Reflect.Obtain;

import java.lang.reflect.Constructor;

import org.junit.Test;

import com.important.Reflect.Person;

public class TestConstructor {

    /**
     * 创建运行时类的对象
     */
    @Test
    public void test1() throws Exception {
        String className = "com.important.Reflect.Person";
        Class clazz = Class.forName(className);
        // 创建运行时类的对象的条件?
        // 1.无参构造器 2.构造器的权限要足够
        Object newInstance = clazz.getConstructor().newInstance();
        Person p = (Person) newInstance;
        System.out.println(p);
    }

    /**
     * 获取运行时类本身声明的public的构造方法
     */
    @Test
    public void test2() throws Exception {
        String className = "com.important.Reflect.Person";
        Class clazz = Class.forName(className);
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    /**
     * 获取运行时类本身声明的所有构造方法
     */
    @Test
    public void test3() throws Exception {
        String className = "com.important.Reflect.Person";
        Class clazz = Class.forName(className);
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            System.out.println(c);
        }
    }

}
