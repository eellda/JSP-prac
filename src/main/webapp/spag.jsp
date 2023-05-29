<%--
  Created by IntelliJ IDEA.
  User: sm
  Date: 2023-05-30
  Time: 오전 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
  int num = 0;
  String num_ = request.getParameter("num");

  if (num_ != null && !num_.equals("")) {
    num = Integer.parseInt(num_);
  }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% if (num % 2 != 0) {%>
  홀수 입니다.
<% } else {%>
  짝수 입니다.
<% }%>
</body>
</html>
