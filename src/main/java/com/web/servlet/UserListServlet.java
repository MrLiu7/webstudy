package com.web.servlet;

import com.domain.User;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 柳继纪
 * @date 3/7/2022
 * @apiNote
 */
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用 UserServiceImpl 完成查询
        UserServiceImpl service = new UserServiceImpl();
        List<User> users = service.findAll();
        //将查询到的集合添加到request域中
        req.setAttribute("users", users);
        //转发到list.jsp页面，显示结果
        //转发不需要虚拟目录
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
