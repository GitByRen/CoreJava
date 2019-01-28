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
        // 不能创建一个确切的泛型类型的数组,而使用通配符创建泛型数组是可以的，因为没有编译时的类型检查，需要开发者自己保证类型转换安全。
        List<?>[] ls = new ArrayList<?>[10];
        List[] list = new ArrayList[10];
        
      //如果允许创建泛型数组
//      Object[] stringLists = new List<String>[];  // compiler error, but pretend it's allowed
//      stringLists[0] = new ArrayList<String>();   // OK
//      An ArrayStoreException should be thrown, but the runtime can't detect it.
//      stringLists[1] = new ArrayList<Integer>();
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
