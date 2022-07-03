<%--
  Created by IntelliJ IDEA.
  User: xiaoliu
  Date: 3/7/2022
  Time: 上午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
    <title>foreach标签</title>
</head>
<body>
打印数字一到十的案例<br>
<c:forEach begin="1" end="10" var="i" step="1">
    ${i}<br>
</c:forEach>
</body>
</html>
