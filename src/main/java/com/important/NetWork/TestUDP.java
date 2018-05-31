package com.important.NetWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

public class TestUDP {

	@Test
	public void send() {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket();
			byte[] b = "我是数据".getBytes();
			// 创建一个数据报，每一个数据报不能大于64K
			DatagramPacket pack = new DatagramPacket(b, b.length, InetAddress.getByName("127.0.0.1"), 9090);
			ds.send(pack);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ds != null) {
				ds.close();
			}
		}
	}

	@Test
	public void receive() {
		DatagramSocket ds = null;
		try {
			// 监听9090端口
			ds = new DatagramSocket(9090);
			byte[] b = new byte[1024];
			// 定义一个数据包，因为要存储接收到的字节数据。因为数据包对象中有更多功能可以提取字节数据中的不同数据信息。
			DatagramPacket dp = new DatagramPacket(b, b.length);
			ds.receive(dp);
			String str = new String(dp.getData(), 0, dp.getLength());
			System.out.println(dp.getAddress().getHostAddress());
			System.out.println(dp.getPort());
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ds != null) {
				ds.close();
			}
		}
	}

}
