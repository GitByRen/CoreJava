package com.important.Jdbc.Driver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTools {

	/**
	 * 释放连接
	 */
	public static void releaseConnection(Connection conn, Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取连接
	 */
	public static Connection getConnection() throws Exception {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		InputStream resourceAsStream = JDBCTools.class.getClass().getClassLoader()
				.getResourceAsStream("jdbc.properties");
		Properties info = new Properties();
		info.load(resourceAsStream);
		driverClass = info.getProperty("driver");
		jdbcUrl = info.getProperty("jdbcUrl");
		user = info.getProperty("user");
		password = info.getProperty("password");
		Class.forName(driverClass);
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		return conn;
	}

}
