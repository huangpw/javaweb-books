package com.huangpw.book.servlet;

import com.huangpw.book.bean.Classes;
import com.huangpw.book.bean.Depart;
import com.huangpw.book.bean.Student;
import com.huangpw.book.service.IClassesService;
import com.huangpw.book.service.IDepartService;
import com.huangpw.book.service.IStudentService;
import com.huangpw.book.service.impl.ClassesServiceImpl;
import com.huangpw.book.service.impl.DepartServiceImpl;
import com.huangpw.book.service.impl.StudentServiceImpl;
import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.service.IRoleService;
import com.huangpw.sys.service.IUserService;
import com.huangpw.sys.service.impl.RoleServiceImpl;
import com.huangpw.sys.service.impl.UserServiceImpl;
import com.huangpw.sys.servlet.BaseServlet;
import com.huangpw.sys.utils.Constant;
import com.huangpw.sys.utils.StringUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "studentServlet", urlPatterns = {"/book/studentServlet"})
public class StudentServlet extends BaseServlet {

    private final IStudentService studentService = new StudentServiceImpl();

    private final IClassesService classesService = new ClassesServiceImpl();

    private final IDepartService departService = new DepartServiceImpl();

    private final IUserService userService = new UserServiceImpl();

    private final IRoleService roleService = new RoleServiceImpl();

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        super.list(req, resp);
        studentService.listPage(pageUtils);
        req.setAttribute(Constant.LIST_PAGE_UTILS, pageUtils);
        // 页面跳转
        req.getRequestDispatcher("/book/student/list.jsp").forward(req, resp);
    }

    @Override
    public void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收表单提交的数据
        String id = req.getParameter("id");
        String account = req.getParameter("account");
        String stuno = req.getParameter("stuno");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String talephone = req.getParameter("talephone");
        String address = req.getParameter("address");
        String wechat = req.getParameter("wechat");
        String classId = req.getParameter("class_id");

        Student student = new Student();
        student.setAccount(Integer.valueOf(account));
        student.setStuno(stuno);
        student.setName(name);
        student.setAge(Integer.parseInt(age));
        student.setGender(gender);
        student.setEmail(email);
        student.setTalephone(talephone);
        student.setAddress(address);
        student.setWechat(wechat);
        if (!StringUtils.isEmpty(classId)) {
            // 班级
            student.setClassId(Integer.parseInt(classId));
            Classes classes = classesService.findById(Integer.parseInt(classId));
            student.setClassname(classes.getName());
            // 院系
            student.setDepartId(classes.getDepartId());
            Depart depart = departService.findById(classes.getDepartId());
            student.setDepartname(depart.getName());
        }

        if(!StringUtils.isEmpty(id)){
            // 更新
            student.setId(Integer.parseInt(id));
            SysUser user = userService.findById(student.getAccount());
            user.setUsername(student.getName());
            user.setNickname(student.getName());
            // 更新账号信息
            userService.updateById(user);
            // 更新学生信息
            studentService.updateById(student);
        } else {
            SysUser user = new SysUser();
            user.setUsername(student.getName());
            user.setNickname(student.getName());
            user.setPassword("123456"); // 默认密码
            // 创建的学生，需要指定默认的角色为学生
            SysRole role = new SysRole();
            role.setName(Constant.ROLE_STUDENT);
            List<SysRole> roles = roleService.list(role);
            if(roles != null && roles.size() > 0){
                role = roles.get(0);
                user.setRoleId(role.getId());
                user.setRolename(role.getName());
            }
            // 创建账号
            userService.save(user);
            // 查询出刚刚创建的账号信息
            user = userService.findByName(user.getUsername());
            student.setAccount(user.getId());

            studentService.save(student);
        }
        // 更新或者添加后需要重新查询相关的数据
        resp.sendRedirect("/book/studentServlet?action=" + Constant.BASE_ACTION_LIST);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        studentService.deleteById(Integer.parseInt(id));
        Student student = studentService.findById(Integer.parseInt(id));
        if(student.getAccount() != null && student.getAccount() > 0){
            userService.deleteById(student.getAccount());
        }
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
            Student classes = studentService.findById(Integer.parseInt(id));
            req.setAttribute("entity", classes);
        }
        // 需要分配院系，所以此处需要查询出所有的院系信息
        List<Depart> departs = departService.list(null);
        req.setAttribute(Constant.DEPARTS, departs);
        // 需要分配班级，所以此处需要查询出所有的班级信息
        List<Classes> classesList = classesService.list(null);
        req.setAttribute(Constant.CLASSESLIST, classesList);

        req.getRequestDispatcher("/book/student/addOrUpdate.jsp").forward(req, resp);
    }
}
