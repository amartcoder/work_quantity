package com.qfnu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * �������ݵ�DAO�ӿ� T:DAO�����ʵ���������
 */
public interface DAO<T> {
    /**
     * ��������ķ���
     */
    void batch(Connection connection, String sql, Object[]... args)
            throws SQLException;

    /**
     * ���ؾ����һ��ֵ����������ĳ���˵���Ϣ��ƽ������
     */
    <E> E getForValue(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * ����һ��T�ļ���
     * 
     * @return
     */
    List<T> getForList(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * ����һ��T�Ķ���
     * 
     * @return
     */
    T get(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * ����ɾ���Ĳ���
     */
    void update(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * ��ѯ����
     */
}
