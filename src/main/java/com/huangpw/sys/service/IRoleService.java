package com.huangpw.sys.service;

import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.bean.SysRoleMenu;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IRoleService {
    /**
     * 查询所有的用户信息
     *@return
     */
    public List<SysRole> list(SysRole role);
    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public void listPage(PageUtils pageUtils);

    /**
     * 保存
     * @param role
     * @return
     */
    public int save(SysRole role);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SysRole findById(int id);

    /**
     * 根据id更新
     * @param role
     * @return
     */
    public int updateById(SysRole role);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public int deleteById(int id);

    /**
     * 查询总数
     * @param pageUtils
     * @return
     */
    public int count(PageUtils pageUtils);

    public boolean checkRoleDispatch(int roleId);

    public void deleteMenuByRoleId(int id);

    public void saveDispatcherMenu(int roleId, int menuId);

    public List<SysRoleMenu> queryByRoleId(int roleId);
}
