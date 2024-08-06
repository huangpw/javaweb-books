package com.huangpw.book.service;

import com.huangpw.book.bean.Student;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IStudentService {

    public List<Student> list(Student student);

    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public void listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Student student);

    public Student findById(int id);

    public int updateById(Student student);

    public int deleteById(int id);
}
