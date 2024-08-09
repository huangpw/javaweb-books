package com.huangpw.book.servlet;

import com.alibaba.fastjson.JSONArray;
import com.huangpw.book.bean.Classes;
import com.huangpw.book.bean.Depart;
import com.huangpw.book.service.IClassesService;
import com.huangpw.book.service.IDepartService;
import com.huangpw.book.service.impl.ClassesServiceImpl;
import com.huangpw.book.service.impl.DepartServiceImpl;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "classesServlet", urlPatterns = {"/book/classesServlet"})
public class ClassesServlet extends BaseServlet {

    private final IClassesService classesService = new ClassesServiceImpl();

    private final IDepartService departService = new DepartServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp);
        classesService.listPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        // 页面跳转
        req.getRequestDispatcher("/book/classes/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收表单提交的数据
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String notes = req.getParameter("notes");
        String departId = req.getParameter("departId");

        Classes classes = new Classes();
        classes.setName(name);
        classes.setNotes(notes);

        if (!StringUtils.isEmpty(departId)) {
            classes.setDepartId(Integer.parseInt(departId));
            Depart depart = departService.findById(Integer.parseInt(departId));
            classes.setDepartname(depart.getName());
        }

        if(!StringUtils.isEmpty(id)){
            // 更新
            classes.setId(Integer.parseInt(id));
            classesService.updateById(classes);
        }else{
            // 保存数据
            classesService.save(classes);
        }
        // 更新或者添加后需要重新查询相关的数据
        resp.sendRedirect("/book/classesServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        classesService.deleteById(Integer.parseInt(id));
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
            Classes classes = classesService.findById(Integer.parseInt(id));
            req.setAttribute("entity", classes);
        }
        // 需要分配院系，所以此处需要查询出所有的院系信息
        List<Depart> departs = departService.list(null);
        req.setAttribute(Constant.DEPARTS, departs);

        req.getRequestDispatcher("/book/classes/addOrUpdate.jsp").forward(req, resp);
    }

    /**
     * 根据院系编号查询班级信息
     * @param req
     * @param resp
     * @throws Exception
     */
    public void findByDepartId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取院系编号
        String departId = req.getParameter("departId");
        Classes cls = new Classes();
        cls.setDepartId(Integer.parseInt(departId));
        List<Classes> list = classesService.list(cls);
        // 把这个集合对象转换为JSON字符串响应给前端
        String json = JSONArray.toJSONString(list);
        // 设置字符编码方式
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
    }
}
