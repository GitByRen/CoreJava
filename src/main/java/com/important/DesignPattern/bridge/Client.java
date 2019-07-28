package com.important.DesignPattern.bridge;

public class Client {

	public static void main(String[] args) {
		// 获取折叠式手机
		Phone phone = new FoldedPhone(new Vivo());
		phone.call();
		
		Phone phone2 = new FoldedPhone(new XiaoMi());
		phone2.call();
	}
	
}
