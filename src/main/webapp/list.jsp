<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function delUser(id) {
            if (confirm("确定删除吗")) {
                location.href = "${pageContext.request.contextPath}/removeUserServlet?id=" + id;
            }
        }

        /*当点击批量删除的时候执行下面的代码*/
        window.onload = function () {
            //先获取到按钮
            let delButton = document.getElementById("delChoose");
            //当该按钮被点击执行下面的方法
            delButton.onclick = function () {
                if (confirm("确定删除所选条目？")) {
                    //获取当前页面的id为idChecks的表单，执行提交方法
                    document.getElementById("idChecks").submit();
                }
            }

            let allCheck = document.getElementById("allChecks");
            allCheck.onclick = function () {
                let userIdChecks = document.getElementsByName("userId");
                for (var i = 0; i < userIdChecks.length; ++i) {
                    userIdChecks[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;border-bottom: 5px">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" class="form-control" id="exampleInputName2" value="null">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" class="form-control" id="exampleInputName3" value="null">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email"  name="email" class="form-control" id="exampleInputEmail2" value="null">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin-bottom: 5px">
        <a class="btn btn-primary" href="add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delChoose">删除选中</a>
    </div>

    <%--这里需要多选，将多条数据提交到服务器，可以使用表单提交--%>
    <form action="${pageContext.request.contextPath}/deleteUsersServlet" id="idChecks">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="allChecks"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <%--JSTL 简化html的书写--%>
            <c:forEach items="${requestScope.pageBean.list}" var="user" varStatus="s">
                <tr>
                        <%--在表单中想被提交，那么就需要赋值name，复选框不给出value值，默认值为on或者是off--%>
                    <td><input type="checkbox" name="userId" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.sex}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>
                        <a class="btn btn-default btn-sm" href="javascript:delUser(${user.id})">删除</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </form>
    <nav aria-label="Page navigation" style="float: left">
        <ul class="pagination pagination-sm">
            <c:if test="${requestScope.pageBean.currentPage==1}">
            <li class="disabled"></c:if>
                <c:if test="${requestScope.pageBean.currentPage!=1}">
            <li></c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=1" aria-label="Previous">
                    <span aria-hidden="true">第一页</span>
                </a>
            </li>
            <c:if test="${requestScope.pageBean.currentPage==1}">
            <li class="disabled"></c:if>
                <c:if test="${requestScope.pageBean.currentPage!=1}">
            <li></c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=${requestScope.pageBean.currentPage-1}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <%--分页处理--%>
            <c:if test="${requestScope.pageBean.currentPage>5&&requestScope.pageBean.currentPage<requestScope.pageBean.totalPage-5}">
                <c:forEach begin="${requestScope.pageBean.currentPage-4}" end="${requestScope.pageBean.currentPage+5}"
                           var="i">
                    <c:if test="${requestScope.pageBean.currentPage==i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.currentPage!=i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=${i}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach></c:if>
            <c:if test="${requestScope.pageBean.currentPage<=5 and requestScope.pageBean.totalPage>10}">
                <c:forEach begin="1" end="10" step="1" var="i">
                    <c:if test="${requestScope.pageBean.currentPage==i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.currentPage!=i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=${i}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach></c:if>
                <c:if test="${requestScope.pageBean.currentPage<=5 and requestScope.pageBean.totalPage<=10}">
                    <c:forEach begin="1" end="${requestScope.pageBean.totalPage}" step="1" var="i">
                        <c:if test="${requestScope.pageBean.currentPage==i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope.pageBean.currentPage!=i}">
                            <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=${i}">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach></c:if>
            <c:if test="${requestScope.pageBean.currentPage>=requestScope.pageBean.totalPage-5}">
                <c:forEach begin="${requestScope.pageBean.totalPage-9}" end="${requestScope.pageBean.totalPage}"
                           step="1" var="i">
                    <c:if test="${requestScope.pageBean.currentPage==i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.currentPage!=i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=${i}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach></c:if>
            <c:if test="${requestScope.pageBean.currentPage==requestScope.pageBean.totalPage}">
            <li class="disabled"></c:if>
            <c:if test="${requestScope.pageBean.currentPage!=requestScope.pageBean.totalPage}">
            <li>
                </c:if><a
                    href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=${requestScope.pageBean.currentPage+1}"
                    aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
            </li>
            <c:if test="${requestScope.pageBean.currentPage==requestScope.pageBean.totalPage}">
            <li class="disabled"></c:if>
            <c:if test="${requestScope.pageBean.currentPage!=requestScope.pageBean.totalPage}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?findPage=${requestScope.pageBean.totalPage}"
                   aria-label="Next">
                    <span aria-hidden="true">末尾页</span>
                </a>
            </li>


            <span style="margin-left: 20px;float: left;margin-top:15px;font-size: 10px">共${requestScope.pageBean.totalPage}页，
                第${requestScope.pageBean.currentPage}页，
            共查询到${requestScope.pageBean.totalCount}条数据</span>
        </ul>
    </nav>
</div>
</body>
</html>
