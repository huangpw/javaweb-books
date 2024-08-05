package com.huangpw.sys.servlet;

import com.huangpw.sys.utils.Constant;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * 处理文件上传操作的Servlet
 */
@WebServlet(name = "uploadServlet", urlPatterns = { "/sys/uploadServlet" })
public class UploadServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 检测是否是文件
        // 这个路径相对当前应用的目录
        // String uploadPath = getServletContext().getRealPath("/") + File.separator + Constant.UPLOAD_DIRECTORY + File.separator; // 设置文件保存目录
        String uploadPath = Constant.UPLOAD_DIRECTORY + File.separator; // 设置文件保存目录
                // 检查shopFile文件夹是否存在，不存在则创建
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdir(); // mkdirs()会创建所有不存在的父目录
        }
        // 文件上传的处理
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // 可以通过ServletFileUpload对上传过来的请求做解析
            // FileItem其实就是客户端表单提交的各个表单域的内容
            List<FileItem> fileItems = upload.parseRequest(req);
            if(fileItems != null && fileItems.size() > 0) {
                for (FileItem fileItem : fileItems) {
                    if(!fileItem.isFormField()) {
                        // 表明该信息上传的文件信息
                        String fileName = new File(fileItem.getName()).getName();
                        // 上传的文件名称
                        fileName = new Date().getTime() + fileName.substring(fileName.lastIndexOf("."));// 重命名文件

                        String filePath = uploadPath + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        fileItem.write(storeFile);
                        //request.setAttribute("message",  "文件上传成功!");
                        // 把生成的文件名称返回给客户端
                        PrintWriter writer = resp.getWriter();
                        writer.write(fileName);
                        writer.flush();
                        writer.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
