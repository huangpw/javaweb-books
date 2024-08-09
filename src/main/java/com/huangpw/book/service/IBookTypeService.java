package com.huangpw.book.service;

import com.huangpw.book.bean.BookType;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IBookTypeService {
    /**
     * 查询所有
     *@return
     */
    public List<BookType> list(BookType bookType);
    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public void listPage(PageUtils pageUtils);

    /**
     * 保存
     * @param bookType
     * @return
     */
    public int save(BookType bookType);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public BookType findById(int id);

    /**
     * 根据id更新
     * @param entity
     * @return
     */
    public int updateById(BookType bookType);

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
