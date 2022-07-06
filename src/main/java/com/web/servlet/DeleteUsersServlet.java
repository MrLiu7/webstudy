package com.web.servlet;

import com.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteUsersServlet")
public class DeleteUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取前端的多条选择，这些选择是具体用户的id数值
        String[] userIds = request.getParameterValues("userId");
        // 将这些id数值提交到service中进行处理
        new UserServiceImpl().deleteUsers(userIds);
        // 转发到查询所有数据的servlet
        request.getRequestDispatcher("/userListServlet").forward(request, response);
    }
}
