package com.important.Jdbc.Base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class JDBCTest2 {

	/**
	 * DatabaseMetaData是描述数据库的元数据,可以得到数据库的一些基本信息(了解)
	 */
	@Test
	public void testDatabaseMetaData() {
		Connection connection = null;
		ResultSet catalogs = null;
		try {
			connection = JDBCTools.getConnection();
			DatabaseMetaData data = connection.getMetaData();

			// 得到版本号
			int version = data.getDatabaseMajorVersion();
			System.out.println(version);

			// 得到连接到数据库的用户名
			String userName = data.getUserName();
			System.out.println(userName);

			// 得到数据库的库
			catalogs = data.getCatalogs();
			while (catalogs.next()) {
				System.out.println(catalogs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(connection, null, catalogs);
		}
	}

	/**
	 * 获取数据库自动生成的主键值
	 */
	@Test
	public void testGetKeyValue() {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = JDBCTools.getConnection();
			String sql = "insert into user(username,password,age,sex,birth) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "sfdsa");
			ps.setString(2, "bbb");
			ps.setInt(3, 33);
			ps.setInt(4, 21);
			// 外面new Date()是java.sql.Date
			ps.setDate(5, new Date(new java.util.Date().getTime()));
			ps.executeUpdate();

			// 通过getGeneratedKeys获取，在resultSet中只有一列GENERATED_KEY，存放主键值
			ResultSet resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				System.out.println(resultSet.getObject(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, ps, null);
		}
	}

}
