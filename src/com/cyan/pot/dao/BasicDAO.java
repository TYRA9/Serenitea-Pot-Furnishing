package com.cyan.pot.dao;

import com.cyan.pot.utils.JDBCUtilsDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
    BasicDAO类，完成所有DAO通用的crud操作。
 */
public class BasicDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object... params) {
        int affectedRows = 0;
        Connection connection = null;

        try {
            connection = JDBCUtilsDruid.getConnection();
            affectedRows = queryRunner.update(connection,sql,params);
            return affectedRows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } /*finally {
            JDBCUtilsDruid.close(null, null, connection);
        }*/
    }

    public List<T> queryMultiply(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;

        try {
            connection = JDBCUtilsDruid.getConnection();

            List<T> query = queryRunner.query(connection,sql,new BeanListHandler<T>(clazz),params);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } /*finally {
            JDBCUtilsDruid.close(null, null, connection);
        }*/
    }

    public T querySingle(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;

        try {
            connection = JDBCUtilsDruid.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } /*finally {
            JDBCUtilsDruid.close(null, null, connection);
        }*/
    }

    public Object queryScalar(String sql, Object... params) {
        Connection connection = null;

        try {
            connection = JDBCUtilsDruid.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler<>(), params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } /*finally {
            JDBCUtilsDruid.close(null,null,connection);
        }*/
    }
}
