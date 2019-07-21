package com.important.DesignPattern.prototype.shallow;

/**
 * 浅拷贝：
 * ①对于数据类型是基本数据类型的成员变量，浅拷贝会直接进行值传递
 * ②对于数据类型是引用数据类型的成员变量，浅拷贝会进行引用传递，也就是将该成员的内存地址复制一份给新的对象
 */
public class Person implements Cloneable {

	private String name;
	private Integer age;
	private Person person;

	public Person(String name, Integer age) {
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	protected Person clone() {
		Person person = null;
		try {
			person = (Person) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
		}
		return person;
	}

}
