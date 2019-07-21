package com.important.DesignPattern.prototype.shallow;

public class ShallowTest {

	public static void main(String[] args) {
		Person person = new Person("len", 12);
		Person pClone = new Person("ovo", 13);
		person.setPerson(pClone);
		
		Person person2 = person.clone();
		Person person3 = person.clone();
		
		// Person引用数据类型没有被clone，只是将该成员的内存地址复制一份给新的对象
		System.out.println("person：" + person + "，hashcode：" + person.getPerson().hashCode());
		System.out.println("person2：" + person2 + "，hashcode：" + person2.getPerson().hashCode());
		System.out.println("person3：" + person3 + "，hashcode：" + person3.getPerson().hashCode());
	}

}
