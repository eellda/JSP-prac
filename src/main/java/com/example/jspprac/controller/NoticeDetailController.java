package com.example.jspprac.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String url = "jdbc:mariadb://localhost:3306/jsp_prac";
        String sql = "SELECT * FROM notice where ID=?";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "1111");
            PreparedStatement st = con.prepareStatement(sql);
            //    1번째 ?에 id
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            // Model
            String title = rs.getString("TITLE");
            Date regdate = rs.getDate("REGDATE");
            String writerId = rs.getString("WRITER_ID");
            int hit = rs.getInt("HIT");
            String files = rs.getString("FILES");
            String content = rs.getString("CONTENT");

            // 담기
            request.setAttribute("title", title);
            request.setAttribute("regdate", regdate);
            request.setAttribute("writerId", writerId);
            request.setAttribute("hit", hit);
            request.setAttribute("files", files);
            request.setAttribute("content", content);

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //redirect
        //forward

        request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
    }
}
