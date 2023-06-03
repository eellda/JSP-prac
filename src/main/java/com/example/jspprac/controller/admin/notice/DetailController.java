package com.example.jspprac.controller.admin.notice;

import com.example.jspprac.entity.Notice;
import com.example.jspprac.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/board/notice/detail")
public class DetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        NoticeService service = new NoticeService();
        Notice notice = service.getNotice(id);
        request.setAttribute("n", notice);

        //redirect
        //forward

        request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/detail.jsp").forward(request, response);
    }
}
