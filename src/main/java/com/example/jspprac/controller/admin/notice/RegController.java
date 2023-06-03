package com.example.jspprac.controller.admin.notice;

import com.example.jspprac.entity.Notice;
import com.example.jspprac.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig(
        location = "/tmp",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setCharacterEncoding("UTF-8");
        //response.setContentType("text/html; charset=UTF-8");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String isOpen = request.getParameter("open");
        boolean pub = false;

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        InputStream fis = filePart.getInputStream();
        String realPath = request.getServletContext().getRealPath("/upload");
        String filePath = realPath + File.separator + fileName;// 자바에서 제공하는 경로 지정방법
        FileOutputStream fos = new FileOutputStream(filePath);

        byte[] buf = new byte[1024];
        int size = 0;
        while ((size = fis.read(buf)) != -1) {
            fos.write(buf, 0, size); // 길이
        }

        if (isOpen != null) {
            pub = true;
        }

        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setPub(pub);
        notice.setWriterId("user1");

        NoticeService service = new NoticeService();
        //service.insertNotice(notice);

        response.sendRedirect("list");

    }
}
