package com.important.Reflect;

@MyAnnotation(value = "atguigu")
public class Person extends Creature<String> implements Comparable, MyInterface {

    public String name;
    private int   age;
    int           id;

    public Person(){
        super();
        // System.out.println("今天天气很闷热");
    }

    private Person(String name, int age){
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @MyAnnotation(value = "atguigu")
    public void show() {
        System.out.println("我是一个人!");
    }

    private Integer display(String nation, Integer i) throws Exception {
        System.out.println("我的国籍是" + nation);
        return i;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static void info() {
        System.out.println("中国人");
    }

    class Bird {

    }

}
