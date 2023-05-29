package com.example.jspprac.Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calc2Servlet", value = "/calc2")
public class Calc2Suvlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        ServletContext application = req.getServletContext();
        String v_ = req.getParameter("v");
        String op = req.getParameter("operator");

        PrintWriter out = resp.getWriter();
        int v = 0;

        if (!v_.equals("")) {
            v = Integer.parseInt(v_);
        }

        if (op.equals("=")) {
            int x = (int) application.getAttribute("value");
            int y = v;
            String operator = (String) application.getAttribute("op");
            int res = 0;

            if (operator.equals("+")) {
                res = x + y;
            } else {
                res = x - y;
            }
            out.printf("result: " + res);
        } else {

            application.setAttribute("value", v);
            application.setAttribute("op", op);
        }

    }
}
