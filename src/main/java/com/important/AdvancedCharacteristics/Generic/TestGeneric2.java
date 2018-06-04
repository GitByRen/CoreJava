package com.important.AdvancedCharacteristics.Generic;

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
