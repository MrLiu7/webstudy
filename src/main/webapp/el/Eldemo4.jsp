<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaoliu
  Date: 2/7/2022
  Time: 下午4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>El隐式运算符</title>
</head>
<body>
<%
    String str1="";
    //String str2;
    request.setAttribute("str1",str1);
    //request.setAttribute("str2",str2); 不能直接添加
    List<String> list = new ArrayList<>();
    request.setAttribute("list",list);
%>
<h3>Empty运算符</h3>
空运算符 empty ${empty requestScope.str}<br>
empty 取反 ${not empty requestScope.str}<br>
集合使用empty直接判空 ${empty requestScope.list}
</body>
</html>
