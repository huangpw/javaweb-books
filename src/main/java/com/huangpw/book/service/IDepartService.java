package com.huangpw.book.service;

import com.huangpw.book.bean.Depart;
import com.huangpw.sys.utils.PageUtils;
import java.util.List;

public interface IDepartService {

    public List<Depart> list(Depart depart);

    public void listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    public int save(Depart depart);

    public Depart findById(int id);

    public int updateById(Depart depart);

    public int deleteById(int id);
}
