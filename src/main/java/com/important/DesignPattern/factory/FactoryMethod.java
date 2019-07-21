package com.important.DesignPattern.factory;

/**
 * 工厂方法模式：定义一个用于创建对象的接口，让子类决定实例化哪一个类，工厂方法使一个类的实例化延迟到其子类。
 */
public class FactoryMethod {
	public static void main(String[] args) {
		IWorkFactory studentWorkFactory = new StudentWorkFactory();
		studentWorkFactory.getWork().doWork();
		studentWorkFactory.getWork().doHomeWork();
		
		TeacherWorkFactory teacherWorkFactory = new TeacherWorkFactory();
		teacherWorkFactory.getWork().doWork();
		teacherWorkFactory.getWork().doHomeWork();
	}
}

interface IWorkFactory {
	Work getWork();
}

class StudentWorkFactory implements IWorkFactory {
	@Override
	public Work getWork() {
		return new StudentWork();
	}
}

class TeacherWorkFactory implements IWorkFactory {
	@Override
	public Work getWork() {
		return new TeacherWork();
	}
}

interface Work {
	void doWork();
	
	default void doHomeWork(){
		System.out.println("aaa");
	}
}

class StudentWork implements Work {
	@Override
	public void doWork() {
		System.out.println("学生写作业");
	}
	
	@Override
	public void doHomeWork(){
		System.out.println("bbb");
	}
}

class TeacherWork implements Work {
	@Override
	public void doWork() {
		System.out.println("老师批作业");
	}
}