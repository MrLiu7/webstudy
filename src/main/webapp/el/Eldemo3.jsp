<%@ page import="com.account.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaoliu
  Date: 2/7/2022
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eldemo3</title>
</head>
<body>
<%
    /*普通对象*/
    User user = new User();
    user.setName("小柳同学");
    user.setAge(21);
    user.setDate(new Date());

    //添加值
    request.setAttribute("u", user);

    //List集合对象
    List<String> list = new ArrayList<>();
    list.add("Java大神");
    list.add("C语言大神");
    list.add("C++语言大神");
    list.add("Python语言大神");
    request.setAttribute("list", list);

    //map集合对象
    Map<String, String> map = new HashMap<>();
    map.put("name","小柳写代码");
    map.put("age","20岁");
    map.put("专业","软件工程");
    request.setAttribute("map",map);
%>
<h3>获取对象的数值</h3>
<%--直接显示对象--%>
${u}<br>
<%--这里可以不需要requestScope，name是通过getName来的，如果不写成getName，那么该方法就不会得到结果--%>
<%--
    通过的是对象的属性来获取值
        setter或getter方法，去掉set或get，将剩余部分，首字母为小写
        setName --> Name -->name
--%>
${requestScope.u.name}<br>
${requestScope.u.age}<br>
${requestScope.u.date}<br>
<%--这里写成birDay，那么在Java类中定义方法应该为getBirDay，就是去掉get，然后后面的属性名称首字母小写，其他字母照抄即可--%>
${requestScope.u.birDay}<br>
<hr>
<%--获取集合中的数值--%>
<h3>获取集合中的数值</h3>
${requestScope.list}<br>
${requestScope.list[0]}<br>
<%--获取Map对象--%>
<hr>
<h3>获取map集合</h3>
${requestScope.map["name"]}<br>
${requestScope.map.专业}<br>
</body>
</html>
