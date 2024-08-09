package com.huangpw.book.servlet;


import com.huangpw.book.bean.Book;
import com.huangpw.book.bean.BookType;
import com.huangpw.book.service.IBookService;
import com.huangpw.book.service.IBookTypeService;
import com.huangpw.book.service.impl.BookServiceImpl;
import com.huangpw.book.service.impl.BookTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "showBookServlet", urlPatterns = {"/book/showBookServlet"})
public class ShowBookServlet extends HttpServlet {

    private IBookTypeService bookTypeService = new BookTypeServiceImpl();

    private IBookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有类型的信息
        List<BookType> list = bookTypeService.list(null);
        if(list != null && list.size() > 0) {
            for (BookType type : list) {
                Book book = new Book();
                book.setTypeId(type.getId());
                List<Book> books = bookService.list(book);
                type.setBooks(books);
            }
        }
        req.setAttribute("list", list);
        req.getRequestDispatcher("/book/book/showBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
