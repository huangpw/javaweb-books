package com.huangpw.book.service.impl;

import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.BorrowCard;
import com.huangpw.book.dao.IBorrowCardDao;
import com.huangpw.book.dao.impl.BorrowCardDaoImpl;
import com.huangpw.book.service.IBorrowCardService;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public class BorrowCardServiceImpl implements IBorrowCardService {

    private final IBorrowCardDao borrowCardDao = new BorrowCardDaoImpl();

    @Override
    public List<BorrowCard> list(BorrowCard borrowCard) {
        return borrowCardDao.list(borrowCard);
    }

    @Override
    public void listPage(PageUtils pageUtils, SysUser user) {
        // 查询分页的数据
        List<BorrowCard> list = borrowCardDao.listPage(pageUtils, user);
        // 查询 满足查询条件的记录数
        int count = borrowCardDao.count(pageUtils, user);
        // 封装分页的数据
        pageUtils.setList(list);
        pageUtils.setTotalCount(count);
    }

    @Override
    public int save(BorrowCard borrowCard) {
        return borrowCardDao.save(borrowCard);
    }

    @Override
    public BorrowCard findById(int id) {
        return borrowCardDao.findById(id);
    }

    @Override
    public int updateById(BorrowCard borrowCard) {
        return borrowCardDao.updateById(borrowCard);
    }

    @Override
    public int deleteById(int id) {
        return borrowCardDao.deleteById(id);
    }

    @Override
    public int count(PageUtils pageUtils, SysUser user) {
        return borrowCardDao.count(pageUtils, user);
    }

    @Override
    public List<BorrowCard> getCanUseCard(Integer userId) {
        return borrowCardDao.getCanUseCard(userId);
    }
}
