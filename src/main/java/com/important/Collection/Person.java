package com.important.Collection;

public class Person implements Comparable {

	private String name;
	private Integer age;

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

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Person) {
			Person p = (Person) o;
			// 如果相同则认为两个对象相同
			int i = this.age.compareTo(p.age);
			if(i == 0) {
				return this.name.compareTo(p.name);
			} else {
				return i;
			}
			// return this.name.compareTo(p.name);
			// return this.age - p.age;(基础类型时可以这样写)
		}
		return 0;
	}

}
