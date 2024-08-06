package com.huangpw.sys.servlet;

import com.huangpw.sys.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "downloadServet", urlPatterns = {"/sys/downloadServlet"})
public class DownloadServet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 下载文件目录
        // String basePath = getServletContext().getRealPath("/") + File.separator + Constant.UPLOAD_DIRECTORY + File.separator;
        String basePath = Constant.UPLOAD_DIRECTORY + File.separator;
        // String basePath = getServletContext().getRealPath("") + File.separator + "upload" + File.separator;

        // 获取需要下载的文件的名称
        String fileName = req.getParameter("fileName");
        FileInputStream in = new FileInputStream(basePath + fileName);
        int size = in.available(); // 获取文件大小
        byte[] data = new byte[size]; // 创建字节数组
        in.read(data); // 读取文件内容到字节数组
        in.close();
        // 判断是否图片 gif,jpg,jpeg,bmp,png
        if(fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".gif") || fileName.contains(".bmp") || fileName.contains(".jpeg")){
            resp.setContentType("image/jpg");
        } else {
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;filename="+  fileName
                    +";filename*=utf-8''"+  URLEncoder.encode(fileName,"UTF-8"));
        }
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
