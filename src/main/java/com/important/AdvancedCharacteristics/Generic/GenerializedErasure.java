package com.important.AdvancedCharacteristics.Generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 泛型擦除带来的问题
 * 
 * 如果泛型类型的类型变量没有限定(<T>) ，那么我们就用Object作为原始类型；
 * 如果有限定(<T extends XClass>)，我们就XClass作为原始类型；
 * 如果有多个限定(<T extends XClass1&XClass2>)，我们就用第一个边界的类型变量XClass1类作为原始类型； 
 */
public class GenerializedErasure {

    @Test
    public void test1() {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }

    @Test
    public void test2() {
        // 不能创建一个确切的泛型类型的数组,而使用通配符创建泛型数组是可以的
        List<?>[] ls = new ArrayList<?>[10];
        List<String>[] list = new ArrayList[10];

        // 下面的代码使用了泛型的数组，是无法通过编译的
//        List<String>[] stringLists = new List<String>[1];
//        List<Integer> intList = Arrays.asList(40);
//        Object[] objects = stringLists;
//        objects[0] = intList;
//        String s = stringLists[0].get(0);
    }

    // 我们无法对泛型代码直接使用instanceof关键字，因为Java编译器在生成代码的时候会擦除所有相关泛型的类型信息
//    public static <E> void rtti(List<E> list) {
//        if (list instanceof ArrayList<Integer>) { // compile-time error
//            // ...
//        }
//    }

    // 使用通配符重新设置边界
    public static void rttis(List<?> list) {
        if (list instanceof ArrayList<?>) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

}
