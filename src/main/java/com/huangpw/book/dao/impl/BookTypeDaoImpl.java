package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.BookType;
import com.huangpw.book.dao.IBookTypeDao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDaoImpl implements IBookTypeDao {
    @Override
    public List<BookType> list(BookType bookType) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_book_type where 1 = 1 ";
        try {
            List<BookType> list = queryRunner.query(sql, new ResultSetHandler<List<BookType>>() {
                @Override
                public List<BookType> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<BookType> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        BookType bookType = new BookType();
                        bookType.setId(resultSet.getInt("id"));
                        bookType.setName(resultSet.getString("name"));
                        bookType.setNotes(resultSet.getString("notes"));
                        bookType.setCreateTime(resultSet.getDate("create_time"));
                        list.add(bookType); // 把查询的记录封装到了集合容器中
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
    public List<BookType> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_book_type limit ?,?";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select * from t_book_type where name like '%"+pageUtils.getKey()+"%' or notes like '%"+pageUtils.getKey()+"%' limit ?,?";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            List<BookType> list = queryRunner.query(sql, new ResultSetHandler<List<BookType>>() {
                @Override
                public List<BookType> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<BookType> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        BookType bookType = new BookType();
                        bookType.setId(resultSet.getInt("id"));
                        bookType.setName(resultSet.getString("name"));
                        bookType.setNotes(resultSet.getString("notes"));
                        bookType.setCreateTime(resultSet.getDate("create_time"));
                        list.add(bookType); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            },startNo, pageUtils.getPageSize());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int save(BookType bookType) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_book_type(name,notes) values(?,?)";
        try {
            return queryRunner.update(sql, bookType.getName(), bookType.getNotes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public BookType findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_book_type where id = ?";
        try {
            BookType bookType = queryRunner.query(sql, new ResultSetHandler<BookType>() {
                @Override
                public BookType handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        BookType bookType = new BookType();
                        bookType.setId(resultSet.getInt("id"));
                        bookType.setName(resultSet.getString("name"));
                        bookType.setNotes(resultSet.getString("notes"));
                        bookType.setCreateTime(resultSet.getDate("create_time"));
                        return bookType;
                    }
                    return null;
                }
            }, id);
            return bookType;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateById(BookType bookType) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_book_type set name=?,notes=? where id=?";
        try {
            return queryRunner.update(sql, bookType.getName(), bookType.getNotes(), bookType.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_book_type where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from t_book_type";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select count(1) from sys_role where name like '%"+pageUtils.getKey()+"%' or notes like '%"+pageUtils.getKey()+"%'";
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
}
