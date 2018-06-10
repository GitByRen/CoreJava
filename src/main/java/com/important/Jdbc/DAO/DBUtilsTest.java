package com.important.Jdbc.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.important.Jdbc.Base.JDBCTools;

public class DBUtilsTest {

	// 1.创建QueryRunner实现类
	QueryRunner queryRunner = new QueryRunner();

	@Test
	public void testDBUtilsUpdate() {
		// 2.使用其方法
		String sql = "delete from user where id = ?";
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			queryRunner.update(conn, sql, 12);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
	}

	/**
	 * QueryHandler的query方法的返回值取决于其ResultSetHandler参数的handle方法的返回值
	 */
	@Test
	public void testDBUtilsQuery() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select id,username,password,age,sex,birth from user";
			Object query = queryRunner.query(conn, sql, new ResultSetHandler<Object>() {

				@Override
				public Object handle(ResultSet rs) throws SQLException {
					List<User> list = new ArrayList<>();
					while (rs.next()) {
						Integer id = rs.getInt(1);
						String username = rs.getString(2);
						String password = rs.getString(3);
						Integer age = rs.getInt(4);
						Integer sex = rs.getInt(5);
						Date date = rs.getDate(6);
						User user = new User(id, username, password, age, sex, date);
						list.add(user);
					}
					return list;
				}
			});

			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
	}

	/**
	 * BeanHandler：把结果集的第一条记录转为创建BeanHandler对象时传入的Class参数对应的对象
	 * 查询的列名要和set()和get()名一样
	 */
	@Test
	public void testBeanHandler() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select id,username,password,age,sex,birth from user where id = ?";
			User query = queryRunner.query(conn, sql, new BeanHandler<>(User.class), 8);
			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
	}

	/**
	 * BeanListHandler：把结果集转为List，该List不为null,但可能为空集
	 */
	@Test
	public void testBeanListHandler() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select id,username,password,age,sex,birth from user";
			List<User> query = queryRunner.query(conn, sql, new BeanListHandler<>(User.class));
			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
	}

	/**
	 * MapHandler：返回SQL对应的第一条记录的Map对象
	 * 键：sql查询的别名或列名  值：列值
	 */
	@Test
	public void testMapHandler() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select id,username name,password,age,sex,birth from user";
			Map<String, Object> query = queryRunner.query(conn, sql, new MapHandler());
			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
	}
	
	/**
	 * MapListHandler：将结果集转为Map的List
	 */
	@Test
	public void testMapListHandler() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select id,username,password,age,sex,birth from user";
			List<Map<String, Object>> query = queryRunner.query(conn, sql, new MapListHandler());
			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
	}
	
	/**
	 * ScalarHandler：把结果集转为一个数值返回
	 */
	@Test
	public void testScalarHandler() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select birth from user where id = ?";
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Object query = queryRunner.query(conn, sql, new ScalarHandler(), 10);
			System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, null, null);
		}
	}
}
