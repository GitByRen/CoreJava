package com.important.Jdbc.Base;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCTest3 {

    @Test
    public void testdbcp1() {
        BasicDataSource ds = null;
        // 1.创建DBCP数据源实例
        ds = new BasicDataSource();

        // 2.为数据源实例指定属性
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setUrl("jdbc:mysql:///ssmh");
        ds.setDriverClassName("com.mysql.jdbc.Driver");

        /*
         * 指定一些可选的属性
         */
        // 1)当这个池被启动时初始化的创建的连接个数
        ds.setInitialSize(10);
        // 2)同一时刻可以同时向数据库中申请的连接数，如设置为负数，则不限制
        ds.setMaxTotal(50); // setMaxActive
        // 3)可以在池中保持空闲的最大连接数，超出设置值之外的空闲连接将被回收
        ds.setMaxIdle(5);
        // 4)可以在池中保持空闲的最小连接数，超出设置值之外的空闲连接将被创建
        ds.setMinIdle(1);
        // 5)等待数据库连接池分配连接的最长时间.单位毫秒.超出时间则抛出异常
        ds.setMaxWaitMillis(1000 * 5);

        // 3.从数据源中获取连接
        try {
            Connection conn = ds.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testdbcp2() {
        try {
            Properties properties = new Properties();
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("dbcp.properties");
            properties.load(in);
            DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getConnection());
            BasicDataSource bds = (BasicDataSource) dataSource;
            System.out.println(bds.getMaxWaitMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testc3p0() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); // loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql:///ssmh");
        cpds.setUser("root");
        cpds.setPassword("root");
        System.out.println(cpds.getConnection());
    }

    @Test
    public void testC3p0WithXml() {
        DataSource dataSource = new ComboPooledDataSource("helloc3p0");
        System.out.println(dataSource);
    }
    
    @Test
    public void testJDBCTools() {
		try {
			Connection connection = JDBCTools.getConnection();
			System.out.println(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
