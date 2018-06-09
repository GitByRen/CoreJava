package com.important.Jdbc.Base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.junit.Test;

public class JDBCTest4 {

	/**
	 * 如何使用JDBC调用存储在数据库中的函数或存储过程
	 */
	@Test
	public void TestCallableStatement() {
		Connection conn = null;
		CallableStatement callableStatement = null;

		try {
			conn = JDBCTools.getConnection();
			// {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
			String sql = "{?= call sum_salary(?, ?)}";
			// 1.创建CallableStatement对象
			callableStatement = conn.prepareCall(sql);
			// 2.通过registerOutParameter注册OUT参数
			callableStatement.registerOutParameter(1, Types.NUMERIC);
			// 3.通过CallableStatement 对象的 setXxx() 方法设定 IN 或 IN OUT 参数. 若想将参数默认值设为null, 可以使用
			// setNull() 方法.
			callableStatement.setInt(2, 80);
			// 4. 通过 CallableStatement 对象的 execute() 方法执行存储过程
			callableStatement.execute();
			// 5. 如果所调用的是带返回参数的存储过程,
			// 还需要通过 CallableStatement 对象的 getXxx() 方法获取其返回值.
			double sumSalary = callableStatement.getDouble(1);
			long empCount = callableStatement.getLong(3);

			System.out.println(sumSalary);
			System.out.println(empCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseConnections(conn, callableStatement, null);
		}
	}

}
