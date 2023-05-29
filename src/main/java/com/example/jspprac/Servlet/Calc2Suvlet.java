package com.example.jspprac.Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calc2Servlet", value = "/calc2")
public class Calc2Suvlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        ServletContext application = req.getServletContext();
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();

        String v_ = req.getParameter("v");
        String op = req.getParameter("operator");

        PrintWriter out = resp.getWriter();

        int v = 0;
        int x = 0;


        if (!v_.equals("")) {
            v = Integer.parseInt(v_);
        }

        if (op.equals("=")) {
            //int x = (int) application.getAttribute("value");
            //int x = (int) session.getAttribute("value");

            for (Cookie c : cookies) {
                if (c.getName().equals("value")) {
                    x =  Integer.parseInt(c.getValue());
                    break;
                }
            }

            //String operator = (String) application.getAttribute("op");
            //String operator = (String) session.getAttribute("op");
            String operator = "";
            int y = v;
            int res = 0;
            for (Cookie c : cookies) {
                if (c.getName().equals("op")) {
                    operator = c.getValue();
                    break;
                }
            }

            if (operator.equals("+")) {
                res = x + y;
            } else {
                res = x - y;
            }
            out.printf("result: " + res);
        } else {
//            application.setAttribute("value", v);
//            application.setAttribute("op", op);

            // session.setAttribute("value", v);
            // session.setAttribute("op", op);

            Cookie valueCookie = new Cookie("value", String.valueOf(v));
            Cookie opCookie = new Cookie("op", op);
            valueCookie.setPath("/calc2"); // 모든 페이지를 요청할 때마다 이 쿠키는 가져오라는 뜻
            valueCookie.setMaxAge(600); // 보관 기간
            opCookie.setPath("/calc2");
            resp.addCookie(valueCookie);
            resp.addCookie(opCookie);
        }

    }
}
