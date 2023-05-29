package com.example.jspprac.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    /*
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        if(req.getMethod().equals("GET")) { // 대문자로 비교
            System.out.println("GET 요청");
        } else if (req.getMethod().equals("POST")) {
            System.out.println("POST 요청");
        }

        super.service(req, resp); // doGet or doPost로 찾아감
    }
    */
     

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGET request");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPOST request");
    }
}
