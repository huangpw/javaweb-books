package com.huangpw.sys.service.impl;

import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.bean.SysRoleMenu;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.dao.IRoleDao;
import com.huangpw.sys.dao.IUserDao;
import com.huangpw.sys.dao.impl.RoleDaoImpl;
import com.huangpw.sys.dao.impl.UserDaoImpl;
import com.huangpw.sys.service.IRoleService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class RoleServiceImpl implements IRoleService {

    private final IRoleDao roleDao = new RoleDaoImpl();
    private IUserDao userDao = new UserDaoImpl();

    @Override
    public List<SysRole> list(SysRole role) {
        return roleDao.list(role);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<SysRole> list = roleDao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = roleDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int save(SysRole role) {
        return roleDao.save(role);
    }

    @Override
    public SysRole findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public int updateById(SysRole role) {
        return roleDao.updateById(role);
    }

    @Override
    public int deleteById(int id) {
        return roleDao.deleteById(id);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return roleDao.count(pageUtils);
    }

    @Override
    public boolean checkRoleDispatch(int roleId) {
        SysUser user = new SysUser();
        user.setRoleId(roleId);
        return userDao.list(user).size() == 0;
    }

    @Override
    public void deleteMenuByRoleId(int id) {
        roleDao.deleteMenuByRoleId(id);
    }

    @Override
    public void saveDispatcherMenu(int roleId, int menuId) {
        roleDao.saveDispatcherMenu(roleId, menuId);
    }

    @Override
    public List<SysRoleMenu> queryByRoleId(int roleId) {
        return roleDao.queryByRoleId(roleId);
    }
}
