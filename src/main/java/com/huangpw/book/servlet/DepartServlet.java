package com.huangpw.book.servlet;

import com.huangpw.book.bean.Depart;
import com.huangpw.book.service.IDepartService;
import com.huangpw.book.service.impl.DepartServiceImpl;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "departServlet", urlPatterns = {"/book/departServlet"})
public class DepartServlet extends BaseServlet {

    private final IDepartService departService = new DepartServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp);
        departService.listPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        // 页面跳转
        req.getRequestDispatcher("/book/depart/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收表单提交的数据
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String notes = req.getParameter("notes");

        Depart depart = new Depart();
        depart.setName(name);
        depart.setNotes(notes);

        if(!StringUtils.isEmpty(id)){
            // 更新
            depart.setId(Integer.parseInt(id));
            departService.updateById(depart);
        }else{
            // 保存数据
            departService.save(depart);
        }
        // 更新或者添加后需要重新查询相关的数据
        resp.sendRedirect("/book/departServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        departService.deleteById(Integer.parseInt(id));
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
        if (!StringUtils.isEmpty(id)) {
            // 说明是更新操作
            Depart depart = departService.findById(Integer.parseInt(id));
            req.setAttribute("entity", depart);
        }
        req.getRequestDispatcher("/book/depart/addOrUpdate.jsp").forward(req, resp);
    }
}
