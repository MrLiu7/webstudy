package com.web.servlet;

import com.domain.PageBean;
import com.domain.User;
import com.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取开始查询的页
        String findPage = request.getParameter("findPage");
        //每一页查询的数目
        String rows = request.getParameter("rows");
        //调用service查询
        PageBean<User> pageBean = new UserServiceImpl().findUserByPage(findPage, rows);
        //将数据存放到request域
        request.setAttribute("pageBean",pageBean);
        //跳转
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
