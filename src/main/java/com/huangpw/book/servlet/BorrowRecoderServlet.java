package com.huangpw.book.servlet;

import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.BorrowCard;
import com.huangpw.book.bean.BorrowRecoder;
import com.huangpw.book.service.IBookService;
import com.huangpw.book.service.IBorrowCardService;
import com.huangpw.book.service.IBorrowRecoderService;
import com.huangpw.book.service.impl.BookServiceImpl;
import com.huangpw.book.service.impl.BorrowCardServiceImpl;
import com.huangpw.book.service.impl.BorrowRecoderServiceImpl;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "borrowRecoderServlet", urlPatterns = {"/book/borrowRecoderServlet"})
public class BorrowRecoderServlet extends BaseServlet {

    private final IBorrowRecoderService borrowRecoderService = new BorrowRecoderServiceImpl();

    private final IBorrowCardService borrowCardService = new BorrowCardServiceImpl();

    private final IBookService bookService = new BookServiceImpl();


    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp);
        borrowRecoderService.listPage(pageUtils, getCurrentLoginUser(req, resp));
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        req.getRequestDispatcher("/book/bookRecoder/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取客户端端提交的数据
        String bookid = req.getParameter("bookid");
        String cardid = req.getParameter("cardid");
        String id = req.getParameter("id");

        SysUser loginUser = this.getCurrentLoginUser(req, resp);
        BorrowRecoder entity = new BorrowRecoder();
        if(!StringUtils.isEmpty(cardid)) {
            // 借书卡不为空
            BorrowCard borrowCard = borrowCardService.findById(Integer.parseInt(cardid));
            entity.setCardid(borrowCard.getId());
            entity.setStuname(borrowCard.getStuname());
            entity.setUserid(borrowCard.getUserid());
            // 新增借阅记录
            if(StringUtils.isEmpty(id)) {
                // 同步更新借书卡的状态
                borrowCard.setState(1);
                borrowCardService.updateById(borrowCard);
            }
        }
        if(!StringUtils.isEmpty(bookid)) {
            Book book = bookService.findById(Integer.parseInt(bookid));
            entity.setBookid(book.getId());
            entity.setBookname(book.getName());
            // 新增借阅记录
            if(StringUtils.isEmpty(id)) {
                book.setState(1);
                bookService.updateById(book);
            }
        }

        if(StringUtils.isEmpty(id)) {
            // 新增
            borrowRecoderService.save(entity);
        } else {
            borrowRecoderService.updateById(entity);
        }

        PrintWriter writer = resp.getWriter();
        writer.write("ok");
        writer.flush();
        //resp.sendRedirect("/book/borrowRecoderServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        borrowRecoderService.deleteById(Integer.parseInt(id));
        PrintWriter writer = resp.getWriter();
        writer.write("ok");
        writer.flush();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 根据id查询
        String id = req.getParameter("id");

        if(!StringUtils.isEmpty(id)) {
            BorrowRecoder borrowRecoder = borrowRecoderService.findById(Integer.parseInt(id));
            req.setAttribute(Constant.UPDATE_ENTITY, borrowRecoder);
        }
        req.getRequestDispatcher("/book/borrowRecoder/addOrUpdate.jsp").forward(req, resp);
    }

    public void backBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        // 根据id查询出对应的借阅记录信息
        BorrowRecoder entity = borrowRecoderService.findById(Integer.parseInt(id));
        entity.setEndtime(new Date()); // 设置归还时间
        borrowRecoderService.updateById(entity);
        // 还需要更新书籍的信息 和 借书卡的状态
        Book book = bookService.findById(entity.getBookid());
        book.setState(0);
        bookService.updateById(book);

        BorrowCard borrowCard = borrowCardService.findById(entity.getCardid());
        borrowCard.setState(0);
        borrowCardService.updateById(borrowCard);

        PrintWriter writer = resp.getWriter();
        writer.write("ok");
        writer.flush();
    }
}
