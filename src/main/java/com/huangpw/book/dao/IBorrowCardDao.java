package com.huangpw.book.dao;

import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.BorrowCard;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IBorrowCardDao {
    /**
     * 查询所有
     *@return
     */
    public List<BorrowCard> list(BorrowCard borrowCard);
    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public List<BorrowCard> listPage(PageUtils pageUtils, SysUser user);

    /**
     * 保存
     * @param borrowCard
     * @return
     */
    public int save(BorrowCard borrowCard);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public BorrowCard findById(int id);

    /**
     * 根据id更新
     * @param entity
     * @return
     */
    public int updateById(BorrowCard borrowCard);

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
    public int count(PageUtils pageUtils, SysUser user);

    public List<BorrowCard> getCanUseCard(Integer userId);
}
