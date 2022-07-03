<%--
  Created by IntelliJ IDEA.
  User: xiaoliu
  Date: 3/7/2022
  Time: 上午10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>choose标签</title>
</head>
<body>
<%
    request.setAttribute("day",10);
%>

<c:choose>
    <c:when test="${requestScope.day == 1}">星期一</c:when>
    <c:when test="${requestScope.day == 2}">星期二</c:when>
    <c:when test="${requestScope.day == 3}">星期三</c:when>
    <c:when test="${requestScope.day == 4}">星期四</c:when>
    <c:when test="${requestScope.day == 5}">星期五</c:when>
    <c:when test="${requestScope.day == 6}">星期六</c:when>
    <c:when test="${requestScope.day == 7}">星期日</c:when>
    <c:otherwise>天数错误</c:otherwise>
</c:choose>
</body>
</html>
