package com.huangpw.book.service.impl;

import com.huangpw.book.bean.Book;
import com.huangpw.book.dao.IBookdao;
import com.huangpw.book.dao.impl.BookDaoImpl;
import com.huangpw.book.service.IBookService;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class BookServiceImpl implements IBookService {

    private final IBookdao bookdao = new BookDaoImpl();


    @Override
    public List<Book> list(Book book) {
        return bookdao.list(book);
    }

    @Override
    public void listPage(PageUtils pageUtils) {
        // 查询分页的数据
        List<Book> list = bookdao.listPage(pageUtils);
        // 查询 满足查询条件的记录数
        int count = bookdao.count(pageUtils);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int save(Book book) {
        return bookdao.save(book);
    }

    @Override
    public Book findById(int id) {
        return bookdao.findById(id);
    }

    @Override
    public int updateById(Book book) {
        return bookdao.updateById(book);
    }

    @Override
    public int deleteById(int id) {
        return bookdao.deleteById(id);
    }

    @Override
    public int count(PageUtils pageUtils) {
        return bookdao.count(pageUtils);
    }
}
