package com.huangpw.sys.service.impl;

import com.huangpw.sys.bean.SysMenu;
import com.huangpw.sys.dao.IMenuDao;
import com.huangpw.sys.dao.impl.MenuDaoImpl;
import com.huangpw.sys.service.IMenuService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class MenuServiceImpl implements IMenuService {

    private final IMenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<SysMenu> list(SysMenu menu) {
        return menuDao.list(menu);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<SysMenu> list = menuDao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = menuDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int save(SysMenu menu) {
        return menuDao.save(menu);
    }

    @Override
    public SysMenu findById(int id) {
        return menuDao.findById(id);
    }

    @Override
    public int updateById(SysMenu menu) {
        return menuDao.updateById(menu);
    }

    @Override
    public int deleteById(int id) {
        return menuDao.deleteById(id);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return menuDao.count(pageUtils);
    }

    @Override
    public List<SysMenu> getAllParent() {
        return menuDao.getAllParent();
    }

    @Override
    public boolean isDispatcher(int id) {
        return menuDao.isDispatcher(id); // 判断是否分配菜单
    }

    @Override
    public boolean haveSubMenu(int id) {
        return menuDao.haveSubMenu(id);
    }
}
