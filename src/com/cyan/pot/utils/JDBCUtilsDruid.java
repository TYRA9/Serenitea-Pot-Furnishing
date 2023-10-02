package com.cyan.pot.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
    基于Druid连接池的工具类
    New version ———— 新增了ThreadLocal静态属性。
 */
public class JDBCUtilsDruid {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        try {
            //properties.load(new FileInputStream("src/druid.properties"));
            /*
                web服务的实际工作路径是在out目录下，因此需要使用类加载器获取文件的真实路径
             */
            properties.load(JDBCUtilsDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

/*  最初版本的getConnection()方法无法保证每次从数据库获取连接时————拿到的是同一个连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
*/

    //从ThreadLocal中获取连接(保证每次从连接池获取的连接都是同一个)
    public static Connection getConnection() {
        Connection connection = threadLocalConn.get();

        /*
            如果当前连接为空，先从数据库中取出一个连接放入threadLocal实例中。
         */
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                //开启一个事务[设置为手动提交]
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            threadLocalConn.set(connection);
        }

        return connection;
    }

    //单独定义一个提交事务的静态方法(DML执行成功--> commit)
    public static void commit() {
        Connection connection = threadLocalConn.get();

        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                //DAO操作完成后，将连接放回连接池中
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /*
            commit后，需要把threadLocal中绑定的connection数据清除掉，
            因为Tomcat底层使用线程池技术，若一个连接长期被ThreadLocal持有，会影响效率。
         */
        threadLocalConn.remove();
    }

    //单独定义一个用于在一次事务控制中回滚DML的静态方法(DML执行失败--> rollback)
    public static void rollback() {
        Connection connection = threadLocalConn.get();

        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        threadLocalConn.remove();
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
