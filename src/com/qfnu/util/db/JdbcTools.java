package com.qfnu.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcTools {
	
	// ʹ�����ݿ����ӳ�����ȡ���ݿ�����--˽�о�̬
    private static DataSource dataSource = null;
    // ���ݿ����ӳ�ֻ��Ҫ��ʼ��һ�ξ͹��ˣ���Ϊһ����Ŀֻ��Ҫһ�����ӳؾ͹���
    static {
        dataSource = new ComboPooledDataSource("expbook");
    }

    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
    // ��ʼ����
    public static void beginTx(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // �ع�����
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // �ύ����
    public static void commit(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // �ر���Դ
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
                // ���ݿ����ӳص�connection���󲢲�����Ľ��йر�
                // ���ǰ����ӹ黹�����ݿ����ӳ���
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
