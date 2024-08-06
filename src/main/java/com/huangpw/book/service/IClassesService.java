package com.huangpw.book.service;

import com.huangpw.book.bean.Classes;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IClassesService {

    public List<Classes> list(Classes classes);
    public void listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Classes classes);

    public Classes findById(int id);

    public int updateById(Classes classes);

    public int deleteById(int id);
}
