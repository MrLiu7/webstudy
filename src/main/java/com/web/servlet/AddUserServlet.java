package com.web.servlet;

import com.domain.User;
import com.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //不设置编码，会导致数据添加的中文乱码
        request.setCharacterEncoding("utf-8");
        //创建user对象
        User user = new User();
        user.setId(0);
        user.setName(request.getParameter("name"));
        user.setGender(request.getParameter("sex"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        user.setAddress(request.getParameter("address"));
        user.setQqNumber(request.getParameter("qq"));
        user.setEmail(request.getParameter("email"));

        //servlet 添加用户到数据库
        boolean addSuccess = new UserServiceImpl().addUser(user);

        //如果添加成功，重定向页面 重定向到 servlet 层
        if (addSuccess){
            response.setContentType("text/html;charset=utf-8");
            response.sendRedirect(request.getContextPath()+"/userListServlet");
        }else {
            //添加失败
            response.getWriter().write("add default");
        }
    }
}
