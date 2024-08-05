package com.huangpw.sys.servlet;

import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.service.IUserService;
import com.huangpw.sys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 自定义的系统用户Servlet
 * 处理客户端对用户的相关请求
 *  1.需要继承自HttpServlet
 *  2.重写对应的doGet和doPost方法
 *  3.通过@WebServlet注解定义相关的信息
 * urlPatterns:这个是提供给客户端访问Servlet的请求路径
 */
//@WebServlet(name = "userServlet", urlPatterns = {"/sys/userServlet"})
public class UserServletOld extends HttpServlet {

    private final IUserService userService = new UserServiceImpl();

    /**
     * 处理客户端的GET请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 处理客户端的POST请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("saveOrUpdate".equals(action)){
            // 表示添加数据
            // 获取客户端端提交的数据
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String nickname = req.getParameter("nickname");
            String img = req.getParameter("img");
            SysUser user = new SysUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setImg(img);
            userService.save(user);
            resp.sendRedirect("/sys/userServlet"); // 重定向到当前Servlet
        } else if("addOrUpdatePage".equals(action)) {
            // 表示跳转到添加或修改页面
            req.getRequestDispatcher("/sys/user/addOrUpdate.jsp").forward(req, resp);
        } else {
            // 表示查询数据
            // 查询所有的用户信息
            List<SysUser> list = userService.list(null);
            req.setAttribute("list", list); // 将用户信息放入请求域中
            req.getRequestDispatcher("/sys/user/list.jsp").forward(req, resp); // 跳转到用户列表页面
        }
    }
}
