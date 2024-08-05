package com.huangpw.sys.service.impl;

import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.dao.IUserDao;
import com.huangpw.sys.dao.impl.UserDaoImpl;
import com.huangpw.sys.service.IUserService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private final IUserDao userDao = new UserDaoImpl();

    @Override
    public List<SysUser> list(SysUser user) {
        return userDao.list(user);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        List<SysUser> list = userDao.listPage(pageUtils);
        int count = userDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return userDao.count(pageUtils);
    }

    @Override
    public int save(SysUser user) {
        return userDao.save(user);
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public int updateById(SysUser user) {
        return userDao.updateById(user);
    }

    @Override
    public int deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public SysUser findByName(String username) {
        return userDao.findByName(username);
    }
}
