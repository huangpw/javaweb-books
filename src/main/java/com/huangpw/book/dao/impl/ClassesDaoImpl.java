package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.Classes;
import com.huangpw.book.bean.Depart;
import com.huangpw.book.dao.IClassesDao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDaoImpl implements IClassesDao {
    @Override
    public List<Classes> list(Classes classes) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_classes";
        try {
            List<Classes> list = queryRunner.query(sql, new ResultSetHandler<List<Classes>>() {
                @Override
                public List<Classes> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Classes> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        Classes classes = new Classes();
                        classes.setId(resultSet.getInt("id"));
                        classes.setName(resultSet.getString("name"));
                        classes.setNotes(resultSet.getString("notes"));
                        classes.setDepartId(resultSet.getInt("depart_id"));
                        classes.setDepartname(resultSet.getString("departname"));
                        classes.setCreateTime(resultSet.getDate("create_time"));
                        list.add(classes); // 把查询的记录封装到了集合容器中
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
    public List<Classes> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_classes limit ?,?";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select * from t_classes where name like '%" + pageUtils.getKey() + "%' or notes like '%" + pageUtils.getKey() + "%' limit ?,? ";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            List<Classes> list = queryRunner.query(sql, new ResultSetHandler<List<Classes>>() {
                @Override
                public List<Classes> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Classes> list = new ArrayList<>();
                    while (resultSet.next()) {

                        // 每次循环一次 user 存储一条数据
                        Classes classes = new Classes();
                        classes.setId(resultSet.getInt("id"));
                        classes.setName(resultSet.getString("name"));
                        classes.setNotes(resultSet.getString("notes"));
                        classes.setDepartId(resultSet.getInt("depart_id"));
                        classes.setDepartname(resultSet.getString("departname"));
                        classes.setCreateTime(resultSet.getDate("create_time"));
                        list.add(classes); // 把查询的记录封装到了集合容器中
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
        String sql = "select count(1) from t_classes ";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select count(1) from t_classes where name like '%" + pageUtils.getKey() + "%' or notes like '%" + pageUtils.getKey() + "%' ";
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
    public int save(Classes classes) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_classes(name,notes,depart_id,departname)values(?,?,?,?) ";
        try {
            return queryRunner.update(sql, classes.getName(), classes.getNotes(), classes.getDepartId(), classes.getDepartname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Classes findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_classes where id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Classes>() {
                @Override
                public Classes handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Classes classes = new Classes();
                        classes.setId(resultSet.getInt("id"));
                        classes.setName(resultSet.getString("name"));
                        classes.setNotes(resultSet.getString("notes"));
                        classes.setDepartId(resultSet.getInt("depart_id"));
                        classes.setDepartname(resultSet.getString("departname"));
                        classes.setCreateTime(resultSet.getDate("create_time"));
                        return classes;
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
    public int updateById(Classes classes) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_classes set name = ? , notes = ?,depart_id=?,departname=? where id = ?";
        try {
            return queryRunner.update(sql, classes.getName(), classes.getNotes(), classes.getDepartId(), classes.getDepartname(), classes.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_classes where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
