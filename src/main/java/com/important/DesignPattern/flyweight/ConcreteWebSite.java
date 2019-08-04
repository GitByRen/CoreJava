package com.important.DesignPattern.flyweight;

public class ConcreteWebSite extends Website {

	public String type;

	public ConcreteWebSite(String type) {
		this.type = type;
	}

	@Override
	public void use(User user) {
		System.out.println("网站的发布形式为：" + type + "，使用的用户：" + user.getName());
	}

}
