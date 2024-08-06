package com.huangpw.book.service.impl;

import com.huangpw.book.bean.Depart;
import com.huangpw.book.dao.IDepartDao;
import com.huangpw.book.dao.impl.DepartDaoImpl;
import com.huangpw.book.service.IDepartService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class DepartServiceImpl implements IDepartService {

    private final IDepartDao departDao = new DepartDaoImpl();

    @Override
    public List<Depart> list(Depart depart) {
        return departDao.list(depart);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<Depart> list = departDao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = departDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return departDao.count(pageUtils);
    }

    @Override
    public int save(Depart depart) {
        return departDao.save(depart);
    }

    @Override
    public Depart findById(int id) {
        return departDao.findById(id);
    }

    @Override
    public int updateById(Depart depart) {
        return departDao.updateById(depart);
    }

    @Override
    public int deleteById(int id) {
        return departDao.deleteById(id);
    }
}
