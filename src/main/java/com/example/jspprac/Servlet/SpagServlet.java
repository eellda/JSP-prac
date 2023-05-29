package com.example.jspprac.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/spag")
public class SpagServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = 0;
        String nums = request.getParameter("num");

        if (nums != null && !nums.equals("")) {
            num = Integer.parseInt(nums);
        }

        String result;

        if (num % 2 != 0) {
            result = "홀수";
        } else {
            result = "짝수";
        }

        request.setAttribute("result", result);
        // redirect 새로운 요청
        // forward 작업 내용 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp"); // 위에 controll 결과를 spag.jsp에게 전달
        dispatcher.forward(request, response);
    }
}
