package com.example.jspprac;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String temp = req.getParameter("cnt");

        int cnt = 10;

        if (temp != null && !temp.equals("")) cnt = Integer.parseInt(temp);

        for (int i = 0; i < cnt; i++) {
            out.println((i + 1) + ": 안녕 servlet <br >");
        }
    }
}