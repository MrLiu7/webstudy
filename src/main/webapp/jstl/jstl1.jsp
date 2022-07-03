<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XiaoLiuPA
  Date: 2/7/2022
  Time: 下午9:20
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <title>if标签</title>
</head>
<body>
<%--
    c:if标签
        test 必须属性，接收boolean表达式
            如果表达式为true，则显示if标签体内容，如果false，则不显示标签体内容

            一般情况下，test属性值会结合el表达式一起使用

        注意：c:if标签没有else情况，想要else情况，则需要再定义一个c:if标签
--%>
<%
    List<String> list =  new ArrayList<>();
    list.add("小柳学编程");
    request.setAttribute("list",list);
%>
<c:if test="false">
    我只显示真的
</c:if>

<c:if test="${not empty list}">
    ${requestScope.list[0]}
</c:if>
</body>
</html>
