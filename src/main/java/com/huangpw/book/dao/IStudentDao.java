package com.huangpw.book.dao;

import com.huangpw.book.bean.Student;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IStudentDao {

    public List<Student> list(Student student);

    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public List<Student> listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Student student);

    public Student findById(int id);

    public int updateById(Student student);

    public int deleteById(int id);
}
