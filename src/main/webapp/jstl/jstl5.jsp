<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: xiaoliu
  Date: 3/7/2022
  Time: 下午12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.account.User" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>jstl案例</title>
</head>
<body>
<%
    List<User> list = new ArrayList<>();
    list.add(new User("柳继纪", 21, new Date()));
    list.add(new User("柏俊杰", 22, new Date()));
    list.add(new User("黄蓉曦", 23, new Date()));
    list.add(new User("刘宇扬", 21, new Date()));
    request.setAttribute("list", list);
%>
<table border="1" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>日期</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="i" varStatus="s">
        <c:if test="${s.count % 2 == 0}">
            <tr bgcolor="#7fffd4">
                <th>s</th>
                <th>${i.name}</th>
                <th>${i.age}</th>
                <th>${i.birDay}</th>
            </tr>
        </c:if>

        <c:if test="${s.count % 2 == 1}">
            <tr bgcolor="#ffc0cb">
                <th>s</th>
                <th>${i.name}</th>
                <th>${i.age}</th>
                <th>${i.birDay}</th>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
