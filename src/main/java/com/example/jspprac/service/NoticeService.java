package com.example.jspprac.service;

import com.example.jspprac.entity.Notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeService {

    public List<Notice> getNoticeList() {
        return getNoticeList("TITLE", "", 1);
    }

    public List<Notice> getNoticeList(int page) {
        return getNoticeList("title", "", page);
    }

    public List<Notice> getNoticeList(String field, String query, int page) {

        List<Notice> list = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM ( " +
                "  SELECT ROW_NUMBER() OVER (ORDER BY regdate DESC) AS num, n.* " +
                "  FROM ( " +
                "    SELECT notice.* " +
                "    FROM notice WHERE " + field + " LIKE ? " +
                "  ) AS n " +
                ") AS subquery " +
                "WHERE num BETWEEN ? AND ? " +
                "ORDER BY regdate DESC";

        // 1, 11, 21, ... = an - 1 + (page - 1) * 10 등차수열
        // 10, 20, 30, ... = page * 10

        String url = "jdbc:mariadb://localhost:3306/jsp_prac";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "1111");
            PreparedStatement st = con.prepareStatement(sql); // ?를 채우기 위함

            st.setString(1, "%" + query + "%");
            st.setInt(2, 1 + (page - 1) * 10);
            st.setInt(3, page * 10);
            ResultSet rs = st.executeQuery();

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

        return list;
    }

    public int getNoticeCount() {
        return getNoticeCount("title", "");
    }

    public int getNoticeCount(String field, String query) {

        int count = 0;

        String sql = "SELECT COUNT(ID) COUNT " +
                "FROM (" +
                "  SELECT ROW_NUMBER() OVER (ORDER BY regdate DESC) AS num, n.* " +
                "  FROM ( " +
                "    SELECT notice.* " +
                "    FROM notice WHERE " + field + " LIKE ? " +
                "  ) AS n " +
                ") AS subquery " +
                "ORDER BY regdate DESC";

        String url = "jdbc:mariadb://localhost:3306/jsp_prac";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "1111");
            PreparedStatement st = con.prepareStatement(sql); // ?를 채우기 위함

            st.setString(1, "%" + query + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public Notice getNotice(int id) {

        Notice notice = null;
        String sql = "SELECT * FROM notice WHERE ID=?";

        String url = "jdbc:mariadb://localhost:3306/jsp_prac";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "1111");
            PreparedStatement st = con.prepareStatement(sql); // ?를 채우기 위함

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int noticeId = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String writerId = rs.getString("WRITER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                notice = new Notice(
                        id,
                        title,
                        regdate,
                        writerId,
                        hit,
                        files,
                        content
                );

            }

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notice;
    }

    public Notice getNextNotice(int id) {

        Notice notice = null;

        String sql = "SELECT * " +
                "FROM notice " +
                "WHERE ID = ( " +
                "  SELECT ID " +
                "  FROM ( " +
                "    SELECT ID, ROW_NUMBER() OVER (ORDER BY REGDATE) AS num " +
                "    FROM notice " +
                "    WHERE REGDATE > (SELECT REGDATE FROM notice WHERE ID = ?) " +
                "  ) AS subquery " +
                "  WHERE num = 1 " +
                ")";

        String url = "jdbc:mariadb://localhost:3306/jsp_prac";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "1111");
            PreparedStatement st = con.prepareStatement(sql); // ?를 채우기 위함

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int noticeId = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String writerId = rs.getString("WRITER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                notice = new Notice(
                        id,
                        title,
                        regdate,
                        writerId,
                        hit,
                        files,
                        content
                );

            }

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notice;
    }

    public Notice getPrevNotice(int id) {

        Notice notice = null;

        String sql = "SELECT * " +
                "FROM notice " +
                "WHERE ID = ( " +
                "  SELECT subquery.ID " +
                "  FROM ( " +
                "    SELECT subquery.ID, ROW_NUMBER() OVER (ORDER BY subquery.ID DESC) AS rn " +
                "    FROM ( " +
                "      SELECT * FROM notice ORDER BY REGDATE DESC " +
                "    ) AS subquery " +
                "    WHERE REGDATE < (SELECT REGDATE FROM notice WHERE ID = ?) " +
                "  ) AS subquery " +
                "  WHERE rn = 1 " +
                ")";

        String url = "jdbc:mariadb://localhost:3306/jsp_prac";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "1111");
            PreparedStatement st = con.prepareStatement(sql); // ?를 채우기 위함

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int noticeId = rs.getInt("ID");
                String title = rs.getString("TITLE");
                Date regdate = rs.getDate("REGDATE");
                String writerId = rs.getString("WRITER_ID");
                int hit = rs.getInt("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                notice = new Notice(
                        id,
                        title,
                        regdate,
                        writerId,
                        hit,
                        files,
                        content
                );

            }

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notice;
    }
}
