package com.huangpw.sys.servlet;

import com.huangpw.sys.bean.SysMenu;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.service.IMenuService;
import com.huangpw.sys.service.IUserService;
import com.huangpw.sys.service.impl.MenuServiceImpl;
import com.huangpw.sys.service.impl.UserServiceImpl;
import com.huangpw.sys.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginServlet", urlPatterns = {"/sys/loginServlet"})
public class LoginServlet extends HttpServlet {

    private final IUserService userService = new UserServiceImpl();

    private final IMenuService menuService = new MenuServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 做登录认证的操作
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 根据账号去数据库查询记录
        SysUser user = userService.findByName(username);
        if (user == null) {
            // 说明账号不存在
            req.setAttribute("msg","登录失败!账号不存在");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        } else if (!password.equals(user.getPassword())) {
            // 说明账号存在，但是密码错误
            req.setAttribute("msg","登录失败!密码错误...");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        } else {
            // 说明登录成功
            // 那么我们需要记录当前登录的用户
            HttpSession session = req.getSession();
            user.setPassword(null);
            session.setAttribute(Constant.LOGIN_USER, user);
            Integer roleId = user.getRoleId();
            if(roleId != null) {
                List<SysMenu> menus = menuService.findMenuByRoleId(roleId);
                session.setAttribute(Constant.LOGIN_MENUS, menus);
            }

            resp.sendRedirect("/main.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
