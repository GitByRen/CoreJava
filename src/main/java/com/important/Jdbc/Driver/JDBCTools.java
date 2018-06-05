package com.important.Jdbc.Driver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTools {

	public static void releaseConnections(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

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
		InputStream resourceAsStream = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
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

	/**
	 * 通用的更新方法 版本1
	 */
	public static void update(String sql) {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.关闭连接
			JDBCTools.releaseConnection(connection, stmt);
		}
	}

	/**
	 * 执行SQL语句，使用PreparedStatement
	 */
	public static void updates(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.关闭连接
			releaseConnections(conn, ps, null);
		}
	}
}
