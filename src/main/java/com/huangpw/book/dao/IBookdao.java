package com.huangpw.book.dao;

import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.Book;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IBookdao {
    /**
     * 查询所有
     *@return
     */
    public List<Book> list(Book book);
    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public List<Book> listPage(PageUtils pageUtils);

    /**
     * 保存
     * @param book
     * @return
     */
    public int save(Book book);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Book findById(int id);

    /**
     * 根据id更新
     * @param entity
     * @return
     */
    public int updateById(Book book);

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
}
