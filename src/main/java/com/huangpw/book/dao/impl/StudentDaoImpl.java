package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.Depart;
import com.huangpw.book.bean.Student;
import com.huangpw.book.dao.IStudentDao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public List<Student> list(Student student) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_student";
        try {
            List<Student> list = queryRunner.query(sql, new ResultSetHandler<List<Student>>() {
                @Override
                public List<Student> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Student> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setAccount(resultSet.getInt("account"));
                        student.setStuno(resultSet.getString("stuno"));
                        student.setName(resultSet.getString("name"));
                        student.setAge(resultSet.getInt("age"));
                        student.setGender(resultSet.getString("gender"));
                        student.setEmail(resultSet.getString("email"));
                        student.setTalephone(resultSet.getString("talephone"));
                        student.setAddress(resultSet.getString("address"));
                        student.setWechat(resultSet.getString("wechat"));
                        student.setClassId(resultSet.getInt("class_id"));
                        student.setClassname(resultSet.getString("classname"));
                        student.setDepartId(resultSet.getInt("depart_id"));
                        student.setDepartname(resultSet.getString("departname"));
                        student.setCreateTime(resultSet.getDate("create_time"));
                        list.add(student); // 把查询的记录封装到了集合容器中
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
    public List<Student> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_student limit ?,?";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select * from t_student where name like '%" + pageUtils.getKey() + "%' or stuno like '%" + pageUtils.getKey() + "%' limit ?,? ";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            List<Student> list = queryRunner.query(sql, new ResultSetHandler<List<Student>>() {
                @Override
                public List<Student> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<Student> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setAccount(resultSet.getInt("account"));
                        student.setStuno(resultSet.getString("stuno"));
                        student.setName(resultSet.getString("name"));
                        student.setAge(resultSet.getInt("age"));
                        student.setGender(resultSet.getString("gender"));
                        student.setEmail(resultSet.getString("email"));
                        student.setTalephone(resultSet.getString("talephone"));
                        student.setAddress(resultSet.getString("address"));
                        student.setWechat(resultSet.getString("wechat"));
                        student.setClassId(resultSet.getInt("class_id"));
                        student.setClassname(resultSet.getString("classname"));
                        student.setDepartId(resultSet.getInt("depart_id"));
                        student.setDepartname(resultSet.getString("departname"));
                        student.setCreateTime(resultSet.getDate("create_time"));
                        list.add(student); // 把查询的记录封装到了集合容器中
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
        String sql = "select count(1) from t_student ";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql = "select count(1) from t_student where name like '%" + pageUtils.getKey() + "%' or stuno like '%" + pageUtils.getKey() + "%' ";
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
    public int save(Student student) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_student(account,name,stuno,age,gender,talephone,email,wechat,address,class_id,classname,depart_id,departname)values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            return queryRunner.update(sql, student.getAccount(), student.getName(), student.getStuno(), student.getAge(), student.getGender(), student.getTalephone(), student.getEmail(), student.getWechat(), student.getAddress(), student.getClassId(), student.getClassname(), student.getDepartId(), student.getDepartname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_student where id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Student>() {
                @Override
                public Student handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setAccount(resultSet.getInt("account"));
                        student.setStuno(resultSet.getString("stuno"));
                        student.setName(resultSet.getString("name"));
                        student.setAge(resultSet.getInt("age"));
                        student.setGender(resultSet.getString("gender"));
                        student.setEmail(resultSet.getString("email"));
                        student.setTalephone(resultSet.getString("talephone"));
                        student.setAddress(resultSet.getString("address"));
                        student.setWechat(resultSet.getString("wechat"));
                        student.setClassId(resultSet.getInt("class_id"));
                        student.setClassname(resultSet.getString("classname"));
                        student.setDepartId(resultSet.getInt("depart_id"));
                        student.setDepartname(resultSet.getString("departname"));
                        student.setCreateTime(resultSet.getDate("create_time"));
                        return student;
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
    public int updateById(Student student) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_student set account=?,name=?,stuno=?,age=?,gender=?,talephone=?,email=?,wechat=?,address=?,class_id=?,classname=?,depart_id=?,departname=? where id = ?";
        try {
            return queryRunner.update(sql, student.getAccount(), student.getName(), student.getStuno(), student.getAge(), student.getGender(), student.getTalephone(), student.getEmail(), student.getWechat(), student.getAddress(), student.getClassId(), student.getClassname(), student.getDepartId(), student.getDepartname(), student.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_student where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
