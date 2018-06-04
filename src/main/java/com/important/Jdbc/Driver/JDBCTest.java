package com.important.Jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class JDBCTest {

	/**
	 * java.sql.Driver是一个接口：数据库厂商必须提供实现了接口的类com.mysql.jdbc.Driver，能从其中获取数据库连接。
	 * @throws SQLException 
	 */
	@Test
	public void testDriver() throws SQLException {
		Driver driver = new Driver();
		String url = "jdbc:mysql://127.0.0.1:3306/login";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "root");
		Connection connect = driver.connect(url, info);
		System.out.println(connect);
	}

	@Test
	public void test() {
	    try {
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
