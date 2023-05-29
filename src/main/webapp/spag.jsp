<%--
  Created by IntelliJ IDEA.
  User: sm
  Date: 2023-05-30
  Time: 오전 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Title</title>
</head>
<%
pageContext.setAttribute("result", "hello");
%>
<body>
    <%=request.getAttribute("result") %> 입니다.
    ${requestScope.result}
    ${names[1]}<br>
    ${notice.title}
    ${result}<br>
    ${empty param.num ? "값이 비어 있습니다" : param.num}<br>
    ${param.num / 2}<br>
    ${header.accept}
</body>
</html>
