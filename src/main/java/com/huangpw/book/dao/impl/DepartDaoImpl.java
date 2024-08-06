package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.Depart;
import com.huangpw.book.dao.IDepartDao;
import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartDaoImpl implements IDepartDao {

    @Override
    public List<Depart> list(Depart depart) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_depart";
        try {
            List<Depart> list = queryRunner.query(sql, new ResultSetHandler<List<Depart>>() {
                @Override
                public List<Depart> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Depart> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        Depart depart = new Depart();
                        depart.setId(resultSet.getInt("id"));
                        depart.setName(resultSet.getString("name"));
                        depart.setNotes(resultSet.getString("notes"));
                        depart.setCreateTime(resultSet.getDate("create_time"));
                        list.add(depart); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            });
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Depart> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_depart limit ?,?";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select * from t_depart where name like '%" + pageUtils.getKey() + "%' or notes like '%" + pageUtils.getKey() + "%' limit ?,? ";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            List<Depart> list = queryRunner.query(sql, new ResultSetHandler<List<Depart>>() {
                @Override
                public List<Depart> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Depart> list = new ArrayList<>();
                    while (resultSet.next()) {

                        // 每次循环一次 user 存储一条数据
                        Depart depart = new Depart();
                        depart.setId(resultSet.getInt("id"));
                        depart.setName(resultSet.getString("name"));
                        depart.setNotes(resultSet.getString("notes"));
                        depart.setCreateTime(resultSet.getDate("create_time"));
                        list.add(depart); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            }, startNo, pageUtils.getPageSize());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from t_depart ";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select count(1) from t_depart where name like '%" + pageUtils.getKey() + "%' or notes like '%" + pageUtils.getKey() + "%' ";
        }
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int save(Depart depart) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_depart(name,notes)values(?,?) ";
        try {
            return queryRunner.update(sql, depart.getName(), depart.getNotes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Depart findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_depart where id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Depart>() {
                @Override
                public Depart handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Depart depart = new Depart();
                        depart.setId(resultSet.getInt("id"));
                        depart.setName(resultSet.getString("name"));
                        depart.setNotes(resultSet.getString("notes"));
                        depart.setCreateTime(resultSet.getDate("create_time"));
                        return depart;
                    }
                    return null; // 返回查询的结果
                }
            }, id);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public int updateById(Depart depart) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_depart set name = ? , notes = ? where id = ?";
        try {
            return queryRunner.update(sql, depart.getName(), depart.getNotes(), depart.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_depart where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
