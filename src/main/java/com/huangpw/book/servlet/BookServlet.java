package com.huangpw.book.servlet;

import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.BookType;
import com.huangpw.book.service.IBookService;
import com.huangpw.book.service.IBookTypeService;
import com.huangpw.book.service.impl.BookServiceImpl;
import com.huangpw.book.service.impl.BookTypeServiceImpl;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "bookServlet", urlPatterns = {"/book/bookServlet"})
public class BookServlet extends BaseServlet {

    private final IBookService bookService = new BookServiceImpl();

    private final IBookTypeService bookTypeService = new BookTypeServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp);
        bookService.listPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        req.getRequestDispatcher("/book/book/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取客户端端提交的数据
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publish = req.getParameter("publish");
        String img = req.getParameter("img");
        String notes = req.getParameter("notes");
        String state = req.getParameter("state");
        String isbn = req.getParameter("isbn");
        String price = req.getParameter("price");
        String typeId = req.getParameter("typeId");

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublish(publish);
        book.setImg(img);
        book.setNotes(notes);
        book.setIsbn(isbn);

        if(!StringUtils.isEmpty(state)) {
            book.setState(Integer.valueOf(state));
        } else {
            book.setState(0);
        }
        if (!StringUtils.isEmpty(price)) {
            book.setPrice(Integer.parseInt(price));
        }

        if(!StringUtils.isEmpty(typeId)) {
            book.setTypeId(Integer.parseInt(typeId));
            BookType bookType = bookTypeService.findById(Integer.parseInt(typeId));
            book.setTypename(bookType.getName());
        }

        if (StringUtils.isEmpty(id)) {
            // 新增
            bookService.save(book);
        } else {
            // 更新
            book.setId(Integer.parseInt(id));
            bookService.updateById(book);
        }
        resp.sendRedirect("/book/bookServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 如果书籍已经有借阅的历史记录，那么这本书就不让删除了
        // TODO 删除数据的校验
        String id = req.getParameter("id");
        bookService.deleteById(Integer.parseInt(id));
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
            Book book = bookService.findById(Integer.parseInt(id));
            req.setAttribute("entity", book);
        }
        // 查询出所有的图书类别信息
        List<BookType> list = bookTypeService.list(null);
        req.setAttribute("types", list);

        req.getRequestDispatcher("/book/book/addOrUpdate.jsp").forward(req, resp);
    }
}
