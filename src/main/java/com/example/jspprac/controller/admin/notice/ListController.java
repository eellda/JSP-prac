package com.example.jspprac.controller.admin.notice;

import com.example.jspprac.entity.NoticeView;
import com.example.jspprac.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/notice/list")
public class ListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String field_ = request.getParameter("f");
        String query_ = request.getParameter("q");
        String page_ = request.getParameter("p");

        String field = "title";
        String query = "";
        int page = 1;

        if (field_ != null && !field_.equals("")) {
            field = field_;
        }

        if (query_ != null && !query_.equals("")) {
            query = query_;
        }

        if (page_ != null && !page_.equals("")) {
            page = Integer.parseInt(page_);
        }

        NoticeService service = new NoticeService();
        List<NoticeView> list = service.getNoticeList(field, query, page);
        int count = service.getNoticeCount(field, query);

        request.setAttribute("list", list);
        request.setAttribute("count", count);
        request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
    }
}
