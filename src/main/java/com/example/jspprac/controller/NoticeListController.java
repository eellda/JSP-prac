package com.example.jspprac.controller;

import com.example.jspprac.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Notice> list = new ArrayList<>();
        String url = "jdbc:mariadb://localhost:3306/jsp_prac";
        String sql = "SELECT * FROM notice";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "1111");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String writerId = rs.getString("WRITER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                Notice notice = new Notice(
                        id,
                        title,
                        regdate,
                        writerId,
                        hit,
                        files,
                        content
                );
                list.add(notice);
            }

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
    }
}
