package com.web.servlet;

import com.Log.Log;
import com.domain.User;
import com.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询user
        User user = new UserServiceImpl().findUserByID(request.getParameter("id"));
        //将user添加到request域
        request.setAttribute("user",user);
        //转发页面
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }
}
