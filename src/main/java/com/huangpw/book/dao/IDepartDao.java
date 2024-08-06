package com.huangpw.book.dao;

import com.huangpw.book.bean.Depart;
import com.huangpw.sys.utils.PageUtils;
import java.util.List;

public interface IDepartDao {

    public List<Depart> list(Depart depart);
    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public List<Depart> listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Depart depart);

    public Depart findById(int id);

    public int updateById(Depart depart);

    public int deleteById(int id);
}
