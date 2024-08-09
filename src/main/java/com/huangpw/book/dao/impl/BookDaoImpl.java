package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.Student;
import com.huangpw.book.dao.IBookdao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookdao {
    @Override
    public List<Book> list(Book book) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_books order by create_time desc";
        if(book != null) {
            if(book.getTypeId() != null && book.getTypeId() > 0) {
                sql = "select * from t_books where type_id="+ book.getTypeId() +" order by create_time desc";
            }
        }
        try {
            List<Book> list = queryRunner.query(sql, new ResultSetHandler<List<Book>>() {
                @Override
                public List<Book> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Book> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        Book book = new Book();
                        book.setId(resultSet.getInt("id"));
                        book.setName(resultSet.getString("name"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setPublish(resultSet.getString("publish"));
                        book.setImg(resultSet.getString("img"));
                        book.setNotes(resultSet.getString("notes"));
                        book.setState(resultSet.getInt("state"));
                        book.setIsbn(resultSet.getString("isbn"));
                        book.setPrice(resultSet.getInt("price"));
                        book.setTypeId(resultSet.getInt("type_id"));
                        book.setTypename(resultSet.getString("typename"));
                        book.setCreateTime(resultSet.getDate("create_time"));
                        list.add(book); // 把查询的记录封装到了集合容器中
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
    public List<Book> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_books order by create_time desc limit ?,?";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select * from t_books where name like '%" + pageUtils.getKey() + "%' or author like '%" + pageUtils.getKey() + "%' order by create_time desc limit ?,? ";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            List<Book> list = queryRunner.query(sql, new ResultSetHandler<List<Book>>() {
                @Override
                public List<Book> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Book> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Book book = new Book();
                        book.setId(resultSet.getInt("id"));
                        book.setName(resultSet.getString("name"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setPublish(resultSet.getString("publish"));
                        book.setImg(resultSet.getString("img"));
                        book.setNotes(resultSet.getString("notes"));
                        book.setState(resultSet.getInt("state"));
                        book.setIsbn(resultSet.getString("isbn"));
                        book.setPrice(resultSet.getInt("price"));
                        book.setTypeId(resultSet.getInt("type_id"));
                        book.setTypename(resultSet.getString("typename"));
                        book.setCreateTime(resultSet.getDate("create_time"));
                        list.add(book); // 把查询的记录封装到了集合容器中
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
    public int save(Book book) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_books(name,author,publish,img,notes,state,isbn,price,type_id,typename)values(?,?,?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql, book.getName(), book.getAuthor(), book.getPublish(), book.getImg(), book.getNotes(), book.getState(), book.getIsbn(), book.getPrice(), book.getTypeId(), book.getTypename());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Book findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_books where id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Book>() {
                @Override
                public Book handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Book book = new Book();
                        book.setId(resultSet.getInt("id"));
                        book.setName(resultSet.getString("name"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setPublish(resultSet.getString("publish"));
                        book.setImg(resultSet.getString("img"));
                        book.setNotes(resultSet.getString("notes"));
                        book.setState(resultSet.getInt("state"));
                        book.setIsbn(resultSet.getString("isbn"));
                        book.setPrice(resultSet.getInt("price"));
                        book.setTypeId(resultSet.getInt("type_id"));
                        book.setTypename(resultSet.getString("typename"));
                        book.setCreateTime(resultSet.getDate("create_time"));
                        return book;
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
    public int updateById(Book book) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_books set name=?,author=?,publish=?,img=?,notes=?,state=?,isbn=?,price=?,type_id=?,typename=? where id = ?";
        try {
            return queryRunner.update(sql, book.getName(), book.getAuthor(), book.getPublish(), book.getImg(), book.getNotes(), book.getState(), book.getIsbn(), book.getPrice(), book.getTypeId(), book.getTypename(), book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_books where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from t_books ";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select count(1) from t_books where name like '%" + pageUtils.getKey() + "%' or author like '%" + pageUtils.getKey() + "%' ";
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
