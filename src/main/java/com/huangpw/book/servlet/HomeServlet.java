package com.huangpw.book.servlet;

import com.alibaba.fastjson.JSONObject;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "homeServlet",urlPatterns = {"/homeServlet"})
public class HomeServlet extends HttpServlet {

    private final IBookTypeService typeService = new BookTypeServiceImpl();

    private final IBookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询统计相关的数据 查询出所有的类别信息。以及对应的类型的图书数量
        List<BookType> types = typeService.list(null);
        // 需要返回的统计的数据
        List<String> typeNames = new ArrayList<>();
        List<Integer> bookNums = new ArrayList<>();
        List<Map<String, Object>> chart2 = new ArrayList<>();
        if(types != null && types.size() >0 ) {
            for (BookType type : types) {
                // 根据 类型的编号查询图书的数量
                Book book = new Book();
                book.setTypeId(type.getId());
                List<Book> list = bookService.list(book);
                typeNames.add(type.getName());
                bookNums.add(list.size());

                Map<String, Object> map = new HashMap<>();
                map.put("name", type.getName());
                map.put("value", list.size());
                chart2.add(map);
            }
        }
        // 返回的统计的数据通过Map统一处理
        Map<String,Object> map = new HashMap<>();
        map.put("typeNames",typeNames);
        map.put("bookNums",bookNums);
        map.put("chart2",chart2);
        resp.setContentType("application/json;charset=utf-8");
        // map 转换为对应的json数据
        String json = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
