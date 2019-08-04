package com.important.DesignPattern.composite;

/**
 * 1、定义：
 * 有时又叫作部分-整体模式，它是一种将对象组合成树状的层次结构的模式，
 * 用来表示“部分-整体”的关系，使用户对单个对象和组合对象具有一致的访问性。
 * 
 * 2、应用场景：
 * ①在需要表示一个对象整体与部分的层次结构的场合。
 * ②要求对用户隐藏组合对象与单个对象的不同，用户可以用统一的接口使用组合结构中的所有对象的场合。
 */
public class Client {

	public static void main(String[] args) {
		OrganizationComponent university = new University("清华大学", "顶尖大学");
		
		OrganizationComponent computer = new College("计算机学院", "计算机学院");
		OrganizationComponent information = new College("信息学院", "信息学院");
		
		computer.add(new Department("软件工程", "软件工程不错"));
		computer.add(new Department("网络工程", "网络工程不错"));
		information.add(new Department("通信工程", "通信工程不好学"));
		information.add(new Department("信息工程", "信息工程好学"));
		
		university.add(computer);
		university.add(information);
		
		university.print();
	}
	
}
