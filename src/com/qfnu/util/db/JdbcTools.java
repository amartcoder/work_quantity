package com.qfnu.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcTools {
	
	// 使用数据库链接池来获取数据库链接--私有静态
    private static DataSource dataSource = null;
    // 数据库连接池只需要初始化一次就够了，因为一个项目只需要一个连接池就够了
    static {
        dataSource = new ComboPooledDataSource("expbook");
    }

    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
    // 开始事务
    public static void beginTx(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 回滚事务
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 提交事务
    public static void commit(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 关闭资源
    public static void releaseDB(ResultSet resultSet,
            PreparedStatement preparedStatement, Statement statement,
            Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                // 数据库链接池的connection对象并不是真的进行关闭
                // 而是把链接归还到数据库连接池中
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
