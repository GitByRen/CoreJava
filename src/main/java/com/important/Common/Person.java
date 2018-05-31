package com.important.Common;

public class Person {
	public String name = "bb";
	public long tel;
	public int age;
	
	{
		name = "aa";
	}
	
	public Person(){}
	
	public Person(String name, Long tel, int age) {
		super();
		this.name = name;
		this.tel = tel;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tel != other.tel)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", tel=" + tel + ", age=" + age + "]";
	}
	
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		} else if (obj instanceof Person) {
//			Person p = (Person) obj;
//			return this.name.equals(p.name) && this.tel == p.tel && this.age == p.age;
//		} else {
//			return false;
//		}
//	}

}

