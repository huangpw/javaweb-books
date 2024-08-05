package com.huangpw.sys.servlet;

import com.huangpw.sys.bean.SysMenu;
import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.dao.impl.MenuDaoImpl;
import com.huangpw.sys.service.IMenuService;
import com.huangpw.sys.service.impl.MenuServiceImpl;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "menuServlet",urlPatterns = "/sys/menuServlet")
public class MenuServlet extends BaseServlet{

    private final IMenuService menuService = new MenuServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<SysMenu> list = menuService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/sys/menu/list.jsp").forward(req,resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取客户端端提交的数据
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String parentId = req.getParameter("parentId");
        String seq = req.getParameter("seq");

        SysMenu menu = new SysMenu();
        menu.setName(name);
        menu.setUrl(url);
        if(!StringUtils.isEmpty(parentId)) {
            menu.setParentId(Integer.parseInt(parentId));
        }
        if(!StringUtils.isEmpty(seq)) {
            menu.setSeq(Integer.parseInt(seq));
        }
        if(StringUtils.isEmpty(id)) {
            // 新增
            menuService.save(menu);
        } else {
            // 修改
            menu.setId(Integer.parseInt(id));
            menuService.updateById(menu);
        }
        resp.sendRedirect("/sys/menuServlet?action=" + Constant.BASE_ACTION_LIST); // 重定向到当前Servlet
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        // 删除菜单判断
        // 1.菜单如果分配给了角色就不能被删除
        boolean flag = menuService.isDispatcher(id);
        String msg = "ok";
        if(flag) {
            // 表示已经被分配了
            msg = "error";
        } else {
            SysMenu entity = menuService.findById(id);
            // 2.需要删除的菜单是父菜单
            if(entity.getParentId() == -1) {
                // 有子菜单的父菜单不能被删除 -- 判断是否有子菜单
                flag = menuService.haveSubMenu(id);
                if(flag){
                    // 有子菜单
                    msg = "error";
                }else{
                    // 父菜单没有子菜单 可以删除
                    menuService.deleteById(id);
                }
            } else {
                // 3.子菜单 可以被删除
                menuService.deleteById(id);
            }
        }

        PrintWriter writer = resp.getWriter();
        writer.write(msg);
        writer.flush();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 根据id查询用户信息
        String id = req.getParameter("id");
        if(!StringUtils.isEmpty(id)){
            // 说明是更新操作。那么我们需要根据id查询出用户的具体的信息
            SysMenu menu = menuService.findById(Integer.parseInt(id));
            req.setAttribute("entity", menu); // 将用户信息放入请求域中
        }

        // 查询所有的父菜单 parentId = -1
        List<SysMenu> list = menuService.getAllParent();
        req.setAttribute("parents", list);

        // 表示跳转到添加或修改页面
        req.getRequestDispatcher("/sys/menu/addOrUpdate.jsp").forward(req, resp);
    }
}
