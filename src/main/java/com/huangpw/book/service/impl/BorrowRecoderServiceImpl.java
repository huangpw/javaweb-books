package com.huangpw.book.service.impl;

import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.BorrowCard;
import com.huangpw.book.bean.BorrowRecoder;
import com.huangpw.book.dao.IBookdao;
import com.huangpw.book.dao.IBorrowRecoderDao;
import com.huangpw.book.dao.impl.BorrowRecoderImpl;
import com.huangpw.book.service.IBookService;
import com.huangpw.book.service.IBorrowRecoderService;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class BorrowRecoderServiceImpl implements IBorrowRecoderService {

    private final IBorrowRecoderDao borrowRecoderDao = new BorrowRecoderImpl();

    private final IBookService bookService = new BookServiceImpl();

    @Override
    public List<BorrowRecoder> list(BorrowRecoder borrowRecoder) {
        return borrowRecoderDao.list(borrowRecoder);
    }

    @Override
    public void listPage(PageUtils pageUtils, SysUser user) {
        // 查询分页的数据
        List<BorrowRecoder> list = borrowRecoderDao.listPage(pageUtils, user);
        // 需要根据 bookid 查询出对应的 图片的 img
        for (BorrowRecoder borrowRecoder : list) {
            if(borrowRecoder.getBookid() != null) {
                Book book = bookService.findById(borrowRecoder.getBookid());
                borrowRecoder.setImg(book.getImg());
            }
        }
        // 查询 满足查询条件的记录数
        int count = borrowRecoderDao.count(pageUtils, user);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int save(BorrowRecoder borrowRecoder) {
        return borrowRecoderDao.save(borrowRecoder);
    }

    @Override
    public BorrowRecoder findById(int id) {
        return borrowRecoderDao.findById(id);
    }

    @Override
    public int updateById(BorrowRecoder borrowRecoder) {
        return borrowRecoderDao.updateById(borrowRecoder);
    }

    @Override
    public int deleteById(int id) {
        return borrowRecoderDao.deleteById(id);
    }

    @Override
    public int count(PageUtils pageUtils, SysUser user) {
        return borrowRecoderDao.count(pageUtils, user);
    }
}
