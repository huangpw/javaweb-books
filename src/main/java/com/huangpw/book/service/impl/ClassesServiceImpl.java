package com.huangpw.book.service.impl;

import com.huangpw.book.bean.Classes;
import com.huangpw.book.bean.Depart;
import com.huangpw.book.dao.IClassesDao;
import com.huangpw.book.dao.IDepartDao;
import com.huangpw.book.dao.impl.ClassesDaoImpl;
import com.huangpw.book.dao.impl.DepartDaoImpl;
import com.huangpw.book.service.IClassesService;
import com.huangpw.book.service.IDepartService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class ClassesServiceImpl implements IClassesService {

    private final IClassesDao classesDao = new ClassesDaoImpl();

    @Override
    public List<Classes> list(Classes classes) {
        return classesDao.list(classes);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<Classes> list = classesDao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = classesDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return classesDao.count(pageUtils);
    }

    @Override
    public int save(Classes classes) {
        return classesDao.save(classes);
    }

    @Override
    public Classes findById(int id) {
        return classesDao.findById(id);
    }

    @Override
    public int updateById(Classes classes) {
        return classesDao.updateById(classes);
    }

    @Override
    public int deleteById(int id) {
        return classesDao.deleteById(id);
    }
}
