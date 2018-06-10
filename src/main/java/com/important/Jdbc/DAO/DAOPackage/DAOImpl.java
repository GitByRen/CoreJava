package com.important.Jdbc.DAO.DAOPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.important.Reflect.ReflectionUtils;

public class DAOImpl<T> implements DAO<T> {

	private QueryRunner queryRunner = null;
	private Class<T> type;

	public DAOImpl() {
		queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void update(Connection conn, String sql, Object... args) throws SQLException {
		queryRunner.update(conn, sql, args);
	}

	@Override
	public T getSingleBean(Connection conn, String sql, Object... args) throws SQLException {
		return queryRunner.query(conn, sql, new BeanHandler<>(type), args);
	}

	@Override
	public List<T> getBeanForList(Connection conn, String sql, Object... args) throws SQLException {
		return queryRunner.query(conn, sql, new BeanListHandler<>(type));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <E> E getForValue(Connection conn, String sql, Object... args) throws SQLException {
		return (E) queryRunner.query(conn, sql, new ScalarHandler(), args);
	}

	@Override
	public void batch(Connection conn, String sql, Object[][] args) throws SQLException {
		// 效率低
//		queryRunner.batch(conn, sql, args);
	}

}
