package com.important.Reflect.Obtain;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

import com.important.Reflect.Person;

public class TestOthers {

    /**
     * 获取运行时类的父类
     */
    @Test
    public void test1() {
        Class clazz = Person.class;
        Class superClass = clazz.getSuperclass();
        System.out.println(superClass);
    }

    /**
     * 获取带泛型的父类
     */
    @Test
    public void test2() {
        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();
        System.out.println(type1);
    }

    /**
     * 获取父类的泛型
     */
    @Test
    public void test3() {
        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();
        ParameterizedType param = (ParameterizedType) type1;
        Type[] actualTypeArguments = param.getActualTypeArguments();
        for (Type type : actualTypeArguments) {
            System.out.println(type.getTypeName());
        }
        System.out.println(((Class) actualTypeArguments[0]).getName());
    }

    /**
     * 获取实现的接口
     */
    @Test
    public void test4() {
        Class clazz = Person.class;
        Class[] interfaces = clazz.getInterfaces();
        for (Class class1 : interfaces) {
            System.out.println(class1);
        }
    }

    /**
     * 获取所在的包
     */
    @Test
    public void test5() {
        Class clazz = Person.class;
        Package package1 = clazz.getPackage();
        System.out.println(package1);
    }

    /**
     * 获取类的注解(RetentionPolicy.CLASS)的不能获取
     */
    @Test
    public void test6() {
        Class clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
