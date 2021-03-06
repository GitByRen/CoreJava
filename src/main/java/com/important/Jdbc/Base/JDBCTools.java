package com.important.Jdbc.Base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCTools {

    // 提交事务
    public static void commit(Connection conn) {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 回滚事务
    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 开始事务
    public static void beginTransaction(Connection conn) {
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭资源：数据库连接池的Connection对象进行close时，并不是真的进行关闭，而是把数据库连接归还到数据库连接池
     */
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

    private static DataSource dataSource = null;
    
    static {
    	dataSource = new ComboPooledDataSource("helloc3p0");
    }
    
    /**
     * 获取连接
     */
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
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
            JDBCTools.releaseConnections(connection, stmt, null);
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
