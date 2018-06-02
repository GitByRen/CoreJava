package com.important.AdvancedCharacteristics.Inner;

public class InnerTest {
	public static void main(String[] args) {
		Person.Dog dog = new Person.Dog();

		Person p = new Person();
//		p.newBird();
		Person.Bird b = p.new Bird();
		b.info();
	}
}

class Person {
	String name;
	int age;

	class Bird {
		private String name;
		int age;

		public Bird() {
		}

		public void setName(String name) {
			System.out.println(name);
			System.out.println(this.name);
			System.out.println(Person.this.name);
		}

		public void info() {
			show();
			show2();
		}
	}

	static class Dog {

	}

	public void show() {
		System.out.println("show()");
	}

	public static void show2() {
		System.out.println("show2()");
	}
	
	public void newBird(){
		new Bird().setName("aa");
	}
}