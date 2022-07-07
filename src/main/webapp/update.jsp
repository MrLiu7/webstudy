<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <%--<base href="<%=basePath%>"/>--%>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<%
    String[] province = {"河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省",
            "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省", "内蒙古自治区",
            "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区", "北京市", "天津市", "上海市", "重庆市", "香港特别行政区", "澳门特别行政区"};
    List<String> provinceList = new ArrayList<>(Arrays.asList(province));
    request.setAttribute("provinceList", provinceList);
%>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <%--隐藏的id，以便数据提交的时候方便后台操作数据库--%>
        <input type="hidden" name="id" value="${requestScope.user.id}">

        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" readonly="readonly"
                   value="${requestScope.user.name}"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${requestScope.user.sex=='男'}">
                <input type="radio" name="sex" value="男" checked="checked"/>男
                <input type="radio" name="sex" value="女"/>女
            </c:if>
            <c:if test="${requestScope.user.sex=='女'}">
                <input type="radio" name="sex" value="男"/>男
                <input type="radio" name="sex" value="女" checked="checked"/>女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" value="${requestScope.user.age}"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <!--创建选择列表-->
            <select name="address" id="address" class="form-control">
                <c:forEach items="${requestScope.provinceList}" var="i">
                    <c:if test="${requestScope.user.address==i}">
                        <option value="${i}" selected="selected">${i}</option>
                    </c:if>
                    <c:if test="${requestScope.user.address!=i}">
                        <option value="${i}">${i}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" id="qq" class="form-control" name="qq" value="${requestScope.user.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" value="${requestScope.user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>