package com.important.Jdbc.DAO.DAOPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 访问数据的DAO接口
 */
public interface DAO<T> {

	/**
	 * INSERT,UPDATE,DELETE
	 * 
	 * @param conn:数据库连接
	 * @param sql:SQL语句
	 * @param args:填充占位符的可变参数
	 * @throws SQLException 
	 */
	void update(Connection conn, String sql, Object... args) throws SQLException;

	/**
	 * 返回一个T的对象
	 * 
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	T getSingleBean(Connection conn, String sql, Object... args) throws SQLException;

	/**
	 * 返回T的一个集合
	 * 
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	List<T> getBeanForList(Connection conn, String sql, Object... args) throws SQLException;

	/**
	 * 返回具体的一个值
	 * 
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	<E> E getForValue(Connection conn, String sql, Object... args) throws SQLException;

	/**
	 * 批量处理
	 * 
	 * @param conn
	 * @param sql
	 * @param args:填充占位符的Object[]类型的可变参数.
	 * @throws SQLException 
	 */
	void batch(Connection conn, String sql, Object[][] args) throws SQLException;

}
