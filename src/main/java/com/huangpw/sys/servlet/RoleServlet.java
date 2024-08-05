package com.huangpw.sys.servlet;

import com.huangpw.sys.bean.SysMenu;
import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.bean.SysRoleMenu;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.service.IMenuService;
import com.huangpw.sys.service.IRoleService;
import com.huangpw.sys.service.impl.MenuServiceImpl;
import com.huangpw.sys.service.impl.RoleServiceImpl;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "roleServlet", urlPatterns = {"/sys/roleServlet"})
public class RoleServlet extends BaseServlet {

    private final IRoleService roleService = new RoleServiceImpl();

    private final IMenuService menuService = new MenuServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp); // 封装了分页的相关的操作
        // TODO 写我们自己的查询处理
        roleService.listPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        // 页面跳转
        req.getRequestDispatcher("/sys/role/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取客户端端提交的数据
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String notes = req.getParameter("notes");

        SysRole role = new SysRole();
        role.setName(name);
        role.setNotes(notes);

        // 获取分配菜单
        String[] menuIds = req.getParameterValues("menuIds");

        if (StringUtils.isEmpty(id)) {
            // 新增
            roleService.save(role);
        } else {
            // 更新
            role.setId(Integer.valueOf(id));
            roleService.updateById(role);
            // 更新分配菜单
            // 1.删除当前角色分配的菜单
            roleService.deleteMenuByRoleId(Integer.parseInt(id));
            // 2.插入新分配的菜单
            if (menuIds != null && menuIds.length > 0) {
                for (String menuId : menuIds) {
                    roleService.saveDispatcherMenu(Integer.parseInt(id), Integer.parseInt(menuId));
                }
            }
        }
        resp.sendRedirect("/sys/roleServlet?action=" + Constant.BASE_ACTION_LIST); // 重定向到当前Servlet
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        boolean flag = roleService.checkRoleDispatch(Integer.parseInt(id));
        PrintWriter writer = resp.getWriter();
        if (flag) {
            // 表示没有被分配，可以删除
            roleService.deleteById(Integer.parseInt(id));
            writer.write("ok");
        } else {
            // 表示不能被删除
            writer.write("error");
        }
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
        // 查询出所有的菜单信息
        List<SysMenu> menus = menuService.list(null);
        req.setAttribute("menus", menus);

        if (!StringUtils.isEmpty(id)) {
            int roleId = Integer.parseInt(id);
            // 根据当前的角色编号 查询出对应的已经分配的菜单信息
            List<SysRoleMenu> roleMenus = roleService.queryByRoleId(roleId);
            if(roleMenus != null && roleMenus.size() > 0) {
                List<Integer> ownerMenus = roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
                for (SysMenu menu : menus) {
                    if (ownerMenus.contains(menu.getId()))
                        menu.setCheck(true);
                }
            }

            // 说明是更新操作。那么我们需要根据id查询出用户的具体的信息
            SysRole user = roleService.findById(roleId);
            req.setAttribute("entity", user); // 将用户信息放入请求域中
        }

        // 表示跳转到添加或修改页面
        req.getRequestDispatcher("/sys/role/addOrUpdate.jsp").forward(req, resp);
    }
}
