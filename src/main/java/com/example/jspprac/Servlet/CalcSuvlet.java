package com.example.jspprac.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calcServlet", value = "/calc")
public class CalcSuvlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String a_ = req.getParameter("x");
        String b_ = req.getParameter("y");
        String op = req.getParameter("operator");
        PrintWriter out = resp.getWriter();

        if (!a_.equals("") && !b_.equals("")) {
            int res = 0;
            if (op.equals("add")) {
                res = Integer.parseInt(a_) + Integer.parseInt(b_);
            } else {
                res = Integer.parseInt(a_) - Integer.parseInt(b_);
            }
            out.printf("result: " + res);
        } else {
            out.printf("다시 입력 해주세요");
        }

    }
}
