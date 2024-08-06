package com.huangpw.sys.service;

import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

/**
 * 系统用户
 * 业务处理的接口定义
 */
public interface IUserService {
   public List<SysUser> list(SysUser user);

   public void listPage(PageUtils pageUtils);

   public int count(PageUtils pageUtils);

   public int save(SysUser user);

   public SysUser findById(Integer id);

   public int updateById(SysUser user);

   public int deleteById(Integer id);

   public SysUser findByName(String username);

}
