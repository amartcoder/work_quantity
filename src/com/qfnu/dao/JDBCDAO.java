package com.qfnu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qfnu.util.db.ReflectionUtils;

public class JDBCDAO<T> implements DAO<T>{
    //QueryRunner是线程安全的
    private QueryRunner queryRunner = null;
    private Class<T> type;
    public JDBCDAO(){
        queryRunner = new QueryRunner();
        type = ReflectionUtils.getSuperGenericType(getClass());
    }
    @Override
    public void batch(Connection connection, String sql, Object[]... args)
            throws SQLException {
        queryRunner.batch(connection, sql, args);
    }
    //已经实现
    @Override
    public <E> E getForValue(Connection connection, String sql, Object... args)
            throws SQLException {
        return (E) queryRunner.query(connection, sql, new ScalarHandler<E>(), args);
    }
    //已经实现
    @Override
    public List<T> getForList(Connection connection, String sql, Object... args)
            throws SQLException {
        return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
    }
    //已经实现
    @Override
    public T get(Connection connection, String sql, Object... args)
            throws SQLException {
        return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
    }
    //已经实现
    @Override
    public void update(Connection connection, String sql, Object... args)
            throws SQLException {
        queryRunner.update(connection, sql, args);
    }
}
