package com.important.Jdbc.DAO.DAOPackage;

import java.sql.Connection;

import org.junit.Test;

import com.important.Jdbc.Base.JDBCTools;

public class UserDaoTest {

	UserDao userDao = new UserDao();

	@Test
	public void testGet() {
		Connection connection = null;

		try {
			connection = JDBCTools.getConnection();
			String sql = "insert into user(username,password) values(?,?)";
			Object[][] args = new Object[1000][];
			for (int i = 0; i < 1000; i++) {
				args[i] = new Object[2];
				args[i][0] = "username" + i;
				args[i][1] = "password" + i;
			}
			userDao.batch(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(connection, null, null);
		}
	}

}
