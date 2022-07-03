<%--
  Created by IntelliJ IDEA.
  User: xiaoliu
  Date: 3/7/2022
  Time: 上午11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
    <title>forEach集合便利</title>
</head>
<body>
<%
    List<String> list = new ArrayList<String>();
    list.add("小柳学编程");
    list.add("小柳爱Java");
    list.add("小柳爱C++");
    list.add("小柳爱C语言");
    request.setAttribute("list",list);
%>

<c:forEach items="${requestScope.list}" var="i" varStatus="s">
    ${i} ${s.index} ${s.count}<br>
</c:forEach>
</body>
</html>
