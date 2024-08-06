package com.huangpw.book.service.impl;

import com.huangpw.book.bean.Depart;
import com.huangpw.book.bean.Student;
import com.huangpw.book.dao.IStudentDao;
import com.huangpw.book.dao.impl.StudentDaoImpl;
import com.huangpw.book.service.IStudentService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    private final IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> list(Student student) {
        return studentDao.list(student);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
// 查询分页的数据
        List<Student> list = studentDao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = studentDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return studentDao.count(pageUtils);
    }

    @Override
    public int save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public int updateById(Student student) {
        return studentDao.updateById(student);
    }

    @Override
    public int deleteById(int id) {
        return studentDao.deleteById(id);
    }
}
