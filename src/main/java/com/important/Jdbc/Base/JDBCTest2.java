package com.important.Jdbc.Base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.important.Jdbc.DAO.DAO;

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

	/**
	 * 插入Blob必须使用PreparedStatement，因为Blob类型的数据无法使用字符串拼写
	 */
	@Test
	public void testInsertBlob() {
		// 插入
//		pstmt.setBlob(new FileInputStream(""));
		
		// 读取blob对象，调用Blob的getBinaryStream获得输入流，然后进行IO操作
//		Blob picture = resultSet.getBlob("image");
//		InputStream is = picture.getBinaryStream();
//		OutputStream os = new FileOutputStream("flower.jpg");
//		byte[] b = new byte[20];
//		int len;
//		while((len = is.read(b)) != -1) {
//			os.write(b, 0, len);
//		}
	}
	
	/**
	 * 事务
	 * 如果多个操作，每个操作使用的是单独的连接，无法保证事务，必须是一个连接
	 * conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	 */
	@Test
	public void testTransaction() {
		Connection conn = null;

		try {
			conn = JDBCTools.getConnection();
			// 开始事务
			conn.setAutoCommit(false);
			
			String sql = "update user set age = ? where id = 13";
			update(conn, sql, 26);
			int i = 10 / 0;
			sql = "update user set age = ? where id = 14";
			update(conn, sql, 28);

			conn.commit();
		} catch (Exception e) {
			// 回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
		
		
		// dao.update每次获取新的连接，无法使用事务
//		DAO dao = new DAO();
//		String sql = "update user set age = ? where id = 13";
//		dao.update(sql, 21);
//		sql = "update user set age = ? where id = 14";
//		dao.update(sql, 20);
	}
	
	public void update(Connection conn, String sql, Object... args) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stmt.setObject(i + 1, args[i]);
			}
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnection(null, stmt);
		}
	}
}
