package com.example.jspprac.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addServlet", value = "/add2")
public class Add2Suvlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String[] num_ = req.getParameterValues("num");

        int sum = 0;

        for (int i = 0; i < num_.length; i++) {
            int num = Integer.parseInt(num_[i]);
            sum += num;
        }
        out.printf("result: " + sum);
    }
}
