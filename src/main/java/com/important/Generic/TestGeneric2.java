package com.important.Generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGeneric2 {
    public static void main(String[] args) {
        Reader<Fruit> reader = new Reader<Fruit>();
        reader.readExact(apples);
        
        List<? extends Fruit> flist1 = new ArrayList<Apple>();
        List<? extends Fruit> flist2 = new ArrayList<Orange>();
        List<? extends Fruit> flist3 = new ArrayList<Fruit>();
//        flist1.add(new Apple());
//        flist1.add(null);
//        flist1.get(0);
        
        List<Apple> apples = new ArrayList<Apple>();
        List<Fruit> fruit = new ArrayList<Fruit>();
        writeExact(apples, new Apple());
        writeExact(fruit, new Apple());
        
        List<? super Apple> list4 = new ArrayList<Apple>();
        List<? super Apple> list5 = new ArrayList<Fruit>();
        List<? super Apple> list6 = new ArrayList<Object>();
        
        //不能创建一个确切的泛型类型的数组,而使用通配符创建泛型数组是可以的
        List<?>[] ls = new ArrayList<?>[10];
        List<String>[] list=new ArrayList[10];
        
        //下面的代码使用了泛型的数组，是无法通过编译的
      /*GenTest<String> genArr[] = new GenTest<String>[2];
        Object[] test = genArr;
        GenTest<StringBuffer> strBuf = new GenTest<StringBuffer>();
        strBuf.setValue(new StringBuffer());
        test[0] = strBuf;
        GenTest<String> ref = genArr[0]; //上面两行相当于使用数组移花接木，让Java编译器把GenTest<StringBuffer>当作了GenTest<String>
        String value = ref.getValue(); // 这里是重点！*/
        
//        List<String>[] stringLists=new List<String>[1];
//        List<Integer> intList = Arrays.asList(40);
//        Object[] objects = stringLists;
//        objects[0]=intList;
//        String s=stringLists[0].get(0);
        
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Orange> orange = Arrays.asList(new Orange());
    static class Reader<T>{
        T readExact(List<? extends T> list) {
            return list.get(0);
        }
    }
    
    static <T> void writeExact(List<? super T> list,T item) {
        list.add(item);
        for (Object t : list) {
            System.out.println(t);
        }
    }
    
}


class Fruit{
}
class Apple extends Fruit{
}
class Orange extends Fruit{
}
class GenTest<T> {
    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T t) {
        value = t;
    }
}
