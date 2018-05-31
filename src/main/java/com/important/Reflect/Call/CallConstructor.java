package com.important.Reflect.Call;

import java.lang.reflect.Constructor;

import org.junit.Test;

import com.important.Reflect.Person;

public class CallConstructor {

    @Test
    public void test1() throws Exception {
        String className = "com.important.Reflect.Person";
        Class clazz = Class.forName(className);
        Constructor cons = clazz.getDeclaredConstructor(String.class, int.class);
        cons.setAccessible(true);
        Person p = (Person) cons.newInstance("罗伟", 20);
        System.out.println(p);
    }

}
