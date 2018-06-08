package com.important.Jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.important.Jdbc.Base.JDBCTools;

public class DAO {

	public void update(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = JDBCTools.getConnection();
			stmt = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stmt.setObject(i + 1, args[i]);
			}
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(connection, stmt, null);
		}
	}

	public <T> T get(Class<T> clazz, String sql, Object... args) {
		T entity = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = JDBCTools.getConnection();
			stmt = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				stmt.setObject(i + 1, args[i]);
			}

			rs = stmt.executeQuery();

			// 2.得到ResultSetMetaData对象
			ResultSetMetaData rsmd = rs.getMetaData();

			// 3.创建Map对象
			Map<String, Object> maps = new HashMap<String, Object>();

			// 4.处理ResultSet
			if (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					maps.put(columnLabel, columnValue);
				}
			}

			// 5.反射
			if (maps.size() > 0) {
				entity = clazz.newInstance();
				for (Map.Entry<String, Object> map : maps.entrySet()) {
					String fieldName = map.getKey();
					Object value = map.getValue();
					// ReflectionUtils.setFieldValue(entity, fieldName, value);
					// 通过BeanUtils的set方法赋值
					BeanUtils.setProperty(entity, fieldName, value);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.关闭连接
			JDBCTools.releaseConnections(connection, stmt, rs);
		}
		return entity;
	}

	public static <T> List<T> getForList(Class<T> clazz, String sql) {
		T entity = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<T> ls = new ArrayList<>();
		try {
			connection = JDBCTools.getConnection();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			List<Map<String, Object>> lists = new ArrayList<>();

			Map<String, Object> maps = null;

			while (rs.next()) {
				maps = new HashMap<String, Object>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					maps.put(columnLabel, columnValue);
				}
				lists.add(maps);
			}

			if (lists.size() > 0) {
				entity = clazz.newInstance();
				for (int i = 0; i < lists.size(); i++) {
					for (Map.Entry<String, Object> entry : maps.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						BeanUtils.setProperty(entity, key, value);
					}
					ls.add(entity);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.关闭连接
			JDBCTools.releaseConnections(connection, stmt, rs);
		}
		return ls;
	}

	public <E> E getForValue(String sql, Object... args) {

		return null;
	}
}
