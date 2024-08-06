package com.huangpw.book.dao;

import com.huangpw.book.bean.Classes;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IClassesDao {

    public List<Classes> list(Classes classes);

    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public List<Classes> listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Classes depart);

    public Classes findById(int id);

    public int updateById(Classes classes);

    public int deleteById(int id);

}
