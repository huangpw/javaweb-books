package com.huangpw.sys.dao;

import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

/**
 * Dao层的接口对象
 */
public interface IUserDao {
    /**
     * 查询所有的用户信息
     * @param user
     * @return
     */
    public List<SysUser> list(SysUser user);

    /**
     * 分页查询用户信息
     * @param pageUtils 分页数据
     * @return
     */
    public List<SysUser> listPage(PageUtils pageUtils);

    public int count(PageUtils pageUtils);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int save(SysUser user);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public SysUser findById(Integer id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateById(SysUser user);

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    public int deleteById(Integer id);

    public SysUser findByName(String username);
}
