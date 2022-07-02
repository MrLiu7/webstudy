<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<body>
<%
    //在域中设置数据
    request.setAttribute("name", "张三");
    session.setAttribute("age", 18);
%>

${requestScope.name}
${sessionScope.age}
</body>
</head>
</html>