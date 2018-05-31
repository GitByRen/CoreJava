package com.important.NetWork;

import java.net.InetAddress;

public class TestInetAddress {

	public static void main(String[] args) throws Exception {
		// InetAddress用来代表IP地址
		InetAddress inet = InetAddress.getByName("www.atguigu.com");
		System.out.println(inet);
		System.out.println(inet.getHostName());
		System.out.println(inet.getHostAddress());
		
		InetAddress inet1 = InetAddress.getLocalHost();
		System.out.println(inet1);
		System.out.println(inet1.getHostName());
		System.out.println(inet1.getHostAddress());
	}
}
