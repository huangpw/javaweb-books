package com.huangpw.sys.dao;

import com.huangpw.sys.bean.SysMenu;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IMenuDao {
    /**
     * 查询所有
     *@return
     */
    public List<SysMenu> list(SysMenu menu);
    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public List<SysMenu> listPage(PageUtils pageUtils);

    /**
     * 保存
     * @param menu
     * @return
     */
    public int save(SysMenu menu);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SysMenu findById(int id);

    /**
     * 根据id更新
     * @param menu
     * @return
     */
    public int updateById(SysMenu menu);

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

    public List<SysMenu> getAllParent();

    public boolean isDispatcher(int id);

    public boolean haveSubMenu(int id);

    public List<SysMenu> findMenuByRoleId(Integer roleId);
}
