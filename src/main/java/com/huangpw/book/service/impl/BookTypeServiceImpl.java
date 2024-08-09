package com.huangpw.book.service.impl;

import com.huangpw.book.bean.BookType;
import com.huangpw.book.dao.IBookTypeDao;
import com.huangpw.book.dao.impl.BookTypeDaoImpl;
import com.huangpw.book.service.IBookTypeService;
import com.huangpw.sys.utils.PageUtils;
import java.util.List;

public class BookTypeServiceImpl implements IBookTypeService {

    private final IBookTypeDao bookTypeDao = new BookTypeDaoImpl();

    @Override
    public List<BookType> list(BookType bookType) {
        return bookTypeDao.list(bookType);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<BookType> list = bookTypeDao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = bookTypeDao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int save(BookType bookType) {
        return bookTypeDao.save(bookType);
    }

    @Override
    public BookType findById(int id) {
        return bookTypeDao.findById(id);
    }

    @Override
    public int updateById(BookType bookType) {
        return bookTypeDao.updateById(bookType);
    }

    @Override
    public int deleteById(int id) {
        return bookTypeDao.deleteById(id);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return bookTypeDao.count(pageUtils);
    }
}
