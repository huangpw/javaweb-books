package com.huangpw.book.servlet;

import com.huangpw.book.bean.BorrowCard;
import com.huangpw.book.bean.Student;
import com.huangpw.book.service.IBorrowCardService;
import com.huangpw.book.service.IStudentService;
import com.huangpw.book.service.impl.BorrowCardServiceImpl;
import com.huangpw.book.service.impl.StudentServiceImpl;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.DateUtils;
import com.huangpw.sys.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "borrowCardServlet", urlPatterns = {"/book/borrowCardServlet"})
public class BorrowCardServlet extends BaseServlet {

    private final IBorrowCardService borrowCardService = new BorrowCardServiceImpl();

    private final IStudentService studentService = new StudentServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp); // 处理分页操作
        // 获取当前登录的用户信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(Constant.LOGIN_USER);
        SysUser user = null;
        if(obj != null) {
            user = (SysUser) obj;
            String rolename = user.getRolename();
            if(rolename.contains("管理员")) {
                // 当前用户是管理员
                user.setIsAdmin(true);
            } else {
                // 当前用户是普通用户
                user.setIsAdmin(false);
            }
        }
        borrowCardService.listPage(pageUtils, user);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        req.getRequestDispatcher("/book/borrowCard/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取客户端端提交的数据
        String id = req.getParameter("id");
        String stuid = req.getParameter("stuid");
        String state =  req.getParameter("state");
        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");

        BorrowCard borrowCard = new BorrowCard();

        if(!StringUtils.isEmpty(state)) {
            borrowCard.setState(Integer.parseInt(state));
        } else {
            borrowCard.setState(0);
        }

        if(!StringUtils.isEmpty(stuid)) {
            int stuId = Integer.parseInt(stuid);
            Student student = studentService.findById(stuId);
            borrowCard.setStuid(stuId);
            borrowCard.setStuname(student.getName());
            borrowCard.setUserid(student.getAccount());
        }

        if(!StringUtils.isEmpty(starttime)) {
            // 做日期格式的字符串的转换
            borrowCard.setStarttime(DateUtils.stringToDate(starttime, DateUtils.DATE_PARTTERN));
        }
        if(!StringUtils.isEmpty(endtime)) {
            // 做日期格式的字符串的转换
            borrowCard.setEndtime(DateUtils.stringToDate(endtime, DateUtils.DATE_PARTTERN));
        }

        if (StringUtils.isEmpty(id)) {
            // 新增
            borrowCardService.save(borrowCard);
        } else {
            // 更新
            borrowCard.setId(Integer.parseInt(id));
            borrowCardService.updateById(borrowCard);
        }
        resp.sendRedirect("/book/borrowCardServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        borrowCardService.deleteById(Integer.parseInt(id));
        PrintWriter writer = resp.getWriter();
        writer.write("ok");
        writer.flush();
    }

    @Override
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    @Override
    public void saveOrUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 需要查询出所有的学生信息
        String id = req.getParameter("id");
        List<Student> students = studentService.list(null);
        req.setAttribute("students", students);
        if(!StringUtils.isEmpty(id)) {
            // 说明是更新操作
            BorrowCard borrowCard = borrowCardService.findById(Integer.parseInt(id));
            req.setAttribute(Constant.UPDATE_ENTITY, borrowCard);
        }
        req.getRequestDispatcher("/book/borrowCard/addOrUpdate.jsp").forward(req, resp);
    }
}
