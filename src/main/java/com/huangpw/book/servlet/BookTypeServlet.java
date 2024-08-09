package com.huangpw.book.servlet;

import com.huangpw.book.bean.BookType;
import com.huangpw.book.service.IBookTypeService;
import com.huangpw.book.service.impl.BookTypeServiceImpl;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "bookTypeServlet", urlPatterns = {"/book/bookTypeServlet"})
public class BookTypeServlet extends BaseServlet {

    private final IBookTypeService bookTypeService = new BookTypeServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp);
        bookTypeService.listPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        req.getRequestDispatcher("/book/bookType/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取客户端端提交的数据
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String notes = req.getParameter("notes");

        BookType bookType = new BookType();
        bookType.setName(name);
        bookType.setNotes(notes);

        if (StringUtils.isEmpty(id)) {
            // 新增
            bookTypeService.save(bookType);
        } else {
            // 更新
            bookType.setId(Integer.parseInt(id));
            bookTypeService.updateById(bookType);
        }
        resp.sendRedirect("/book/bookTypeServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        bookTypeService.deleteById(Integer.parseInt(id));
        PrintWriter writer = resp.getWriter();
        writer.write("ok");
        writer.flush();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        if(!StringUtils.isEmpty(id)) {
            // 说明是更新操作
            BookType bookType = bookTypeService.findById(Integer.parseInt(id));
            req.setAttribute("entity", bookType);
        }
        req.getRequestDispatcher("/book/bookType/addOrUpdate.jsp").forward(req, resp);
    }
}
