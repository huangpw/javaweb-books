package com.huangpw.sys.servlet;

import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.service.IRoleService;
import com.huangpw.sys.service.IUserService;
import com.huangpw.sys.service.impl.RoleServiceImpl;
import com.huangpw.sys.service.impl.UserServiceImpl;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = {"/sys/userServlet"})
public class UserServlet extends BaseServlet {

    private final IUserService userService = new UserServiceImpl();

    private final IRoleService roleService = new RoleServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 调用父类中的方法完成分页数据的处理
        super.list(req, resp);
        // 分页查询
        userService.listPage(pageUtils);
        // 把查询的用户数据存储在 request作用域中
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils); // 将用户信息放入请求域中
        // 通过服务端转发的方式跳转页面
        req.getRequestDispatcher("/sys/user/list.jsp").forward(req, resp); // 跳转到用户列表页面
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取客户端端提交的数据
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String img = req.getParameter("img");
        String roleId = req.getParameter("roleId");

        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setImg(img);
        if(!StringUtils.isEmpty(roleId)) {
            // 根据角色Id查询出对应的信息
            SysRole role = roleService.findById(Integer.parseInt(roleId));
            user.setRoleId(Integer.parseInt(roleId));
            user.setRolename(role.getName());
        }
        if(StringUtils.isEmpty(id)) {
            // 说明是添加操作
            userService.save(user);
        } else {
            // 说明是更新操作
            user.setId(Integer.valueOf(id));
            userService.updateById(user);
        }
        resp.sendRedirect("/sys/userServlet?action=" + Constant.BASE_ACTION_LIST); // 重定向到当前Servlet
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        int count = userService.deleteById(Integer.valueOf(id));
        PrintWriter writer = resp.getWriter();
        writer.write(count+"");
        writer.flush();
        writer.close();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 根据id查询用户信息
        String id = req.getParameter("id");
        // 查询出所有的角色信息
        List<SysRole> roles = roleService.list(null);
        req.setAttribute("roles", roles); // 将角色信息放入请求域中

        if(!StringUtils.isEmpty(id)){
            // 说明是更新操作。那么我们需要根据id查询出用户的具体的信息
            SysUser user = userService.findById(Integer.valueOf(id));
            req.setAttribute("entity", user); // 将用户信息放入请求域中
        }

        // 表示跳转到添加或修改页面
        req.getRequestDispatcher("/sys/user/addOrUpdate.jsp").forward(req, resp);
    }
}
