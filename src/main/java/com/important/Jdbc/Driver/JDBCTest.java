package com.important.Jdbc.Driver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class JDBCTest {

    /**
     * java.sql.Driver是一个接口：数据库厂商必须提供实现了接口的类com.mysql.jdbc.Driver，能从其中获取数据库连接。
     * 
     * @throws SQLException
     */
    @Test
    public void testDriver() throws SQLException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://127.0.0.1:3306/login";
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "root");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    /**
     * DriverManager 1). 通过重载的getConnection()方法获取数据库连接，较为方便 2). 可以同时管理多个驱动程序 3). 给getConnection传入参数时，通过url连接不同的数据库
     */
    @Test
    public void testDriverManager() {
        String driverClass = null;
        String jdbcUrl = null;
        String user = null;
        String password = null;
        Connection conn = null;
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            Properties info = new Properties();
            info.load(resourceAsStream);
            driverClass = info.getProperty("driver");
            jdbcUrl = info.getProperty("jdbcUrl");
            user = info.getProperty("user");
            password = info.getProperty("password");
            Class.forName(driverClass);
            conn = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.Statement用于执行SQL语句的对象 1). 通过Connection的createStatement获取 2).通过executeUpdate(sql)可以执行SQL语句
     * 2.Connection、Statement都是应用程序和数据库的连接资源，使用后要关闭 3.先关闭后获取的
     */
    @Test
    public void testStatement() {
        // 1.获取数据库连接
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = JDBCTools.getConnection();
            // 2.准备插入的SQL语句
            String sql = "insert into user(username,password,age,sex) values('AA','BB',1,2)";
            // 3.执行插入
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.关闭连接
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通用的更新方法 版本1
     */
    public void update(String sql) {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = JDBCTools.getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.关闭连接
            JDBCTools.releaseConnection(connection, stmt);
        }
    }

    /**
     * ResultSet：结果集，封装了使用JDBC进行查询的结果. 1.ResultSet返回的实际上就是一张数据表，有一个指针指向数据表的第一行的前面
     * 2.通过getXxx(index)或getXxx(columnName)获取每一列的值
     */
    @Test
    public void testResultSet() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = JDBCTools.getConnection();
            stmt = connection.createStatement();
            String sql = "SELECT id,username,password,age,sex FROM user";
            rs = stmt.executeQuery(sql);
            // 处理ResultSet
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString("username");
                int age = rs.getInt(4);
                System.out.println(id + " " + name + " " + age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.关闭连接
            JDBCTools.releaseConnections(connection, stmt, rs);
        }
    }

    /**
     * 以面向对象的思想编写JDBC程序
     */
    public void addNewUser(User user) {
        String sql = "INSERT INTO user values(null,'" + user.getUserName() + "','" + user.getPassword() + "',"
                     + user.getAge() + "," + user.getSex() + ")";
        System.out.println(sql);
        update(sql);
    }

    @Test
    public void testAddNewUser() {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("username:");
        user.setUserName(sc.next());
        System.out.println("password:");
        user.setPassword(sc.next());
        System.out.println("age:");
        user.setAge(sc.nextInt());
        System.out.println("sex:");
        user.setSex(sc.nextInt());
        addNewUser(user);
        sc.close();
    }
}
