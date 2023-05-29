<%--
  Created by IntelliJ IDEA.
  User: sm
  Date: 2023-05-30
  Time: 오전 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
String temp = request.getParameter("cnt");
int cnt = 20;
if (temp != null && !temp.equals("")) cnt = Integer.parseInt(temp);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<% for (int i = 0; i < cnt; i++) { %>
  안녕 servlet <br >
<% } %>
</body>
</html>
