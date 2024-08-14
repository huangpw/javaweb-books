package com.huangpw.sys.servlet;

import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 系统公共的Servlet
 * 我们约定客户端提交的请求中的action就是在Servlet要处理的方法的名称
 */
public abstract class BaseServlet extends HttpServlet {

    protected PageUtils pageUtils ;

    /**
     * 通过反射的方式调用对象中的Action对应的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取传递的Action参数
        String action = req.getParameter("action");
        try {
            // 获取当前对象对应的Method对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            // 调用方法执行的时候出现了异常
            try {
                this.list(req, resp);
            } catch (Exception ex) {
                e.printStackTrace();
            }
        }
    }

    // 定义增删查改的方法
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        pageUtils = new PageUtils();
        String pn = req.getParameter("pageNum");
        String ps = req.getParameter("pageSize");
        String key = req.getParameter("key");
        // 声明默认的分页参数
        int pageSize = 10; // 默认每页显示10条
        int pageNum = 1; // 默认第一页

        if(!StringUtils.isEmpty(pn)) {
            pageNum = Integer.parseInt(pn);
        }
        if(!StringUtils.isEmpty(ps)) {
            pageSize = Integer.parseInt(ps);
        }

        pageUtils.setPageNum(pageNum);
        pageUtils.setPageSize(pageSize);
        pageUtils.setKey(key);

        getCurrentLoginUser(req, resp);

    };
    public abstract void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception;
    public abstract void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    /**
     * 获取用户是否管理员
     * @return
     */
    public SysUser getCurrentLoginUser(HttpServletRequest req, HttpServletResponse resp) {
        // 获取当前登录的用户信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        SysUser loginUser = null; // 当前登录的用户信息
        if (obj != null) {
            loginUser = (SysUser) obj;
            String rolename = loginUser.getRolename();
            if(rolename.contains("管理员")) {
                // 当前用户是管理员
                loginUser.setIsAdmin(true);
            } else {
                // 当前用户是普通用户
                loginUser.setIsAdmin(false);
            }
        }
        return loginUser;
    }
}
