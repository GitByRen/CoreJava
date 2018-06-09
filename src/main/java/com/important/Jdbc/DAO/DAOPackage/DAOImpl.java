package com.important.Jdbc.DAO.DAOPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class DAOImpl<T> implements DAO<T> {

	private QueryRunner queryRunner = null;
	private Class<T> type;

	public DAOImpl() {
		queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void update(Connection conn, String sql, Object... args) {

	}

	@Override
	public T get(Connection conn, String sql, Object... args) throws SQLException {
		return queryRunner.query(conn, sql, new BeanHandler<>(type), args);
	}

	@Override
	public List<T> getForList(Connection conn, String sql, Object... args) {

		return null;
	}

	@Override
	public <E> E getForValue(Connection conn, String sql, Object... args) {

		return null;
	}

	@Override
	public void batch(Connection conn, String sql, Object... args) {

	}

}
