package com.qfnu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 访问数据的DAO接口 T:DAO处理的实体类的类型
 */
public interface DAO<T> {
    /**
     * 批量处理的方法
     */
    void batch(Connection connection, String sql, Object[]... args)
            throws SQLException;

    /**
     * 返回具体的一个值：总人数，某个人的信息，平均工资
     */
    <E> E getForValue(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * 返回一个T的集合
     * 
     * @return
     */
    List<T> getForList(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * 返回一个T的对象
     * 
     * @return
     */
    T get(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * 增、删、改操作
     */
    void update(Connection connection, String sql, Object... args)
            throws SQLException;

    /**
     * 查询方法
     */
}
