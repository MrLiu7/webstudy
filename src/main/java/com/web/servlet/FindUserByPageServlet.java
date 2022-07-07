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
        request.setCharacterEncoding("UTF-8");
        // 获取开始查询的页
        String findPage = request.getParameter("findPage");
        // 每一页查询的数目
        String rows = request.getParameter("rows");
        // 调用service查询
        PageBean<User> pageBean = new UserServiceImpl().findUserByPage(request.getParameterMap(), findPage, rows);
        // 将数据存放到request域
        request.setAttribute("pageBean", pageBean);
        // 此处处理前台分页栏开始展示数据的页号
        // 返回前端的当前页面号
        int currentPage = pageBean.getCurrentPage();
        // 总页号
        int totalPage = pageBean.getTotalPage();
        // 逻辑处理
        int left = Math.max(currentPage - 4, 1);
        int right = Math.min(currentPage + 5, totalPage);
        if (right - left < 9) {
            if (left == 1) {
                right = Math.min(totalPage, right + (right - left));
            }
            if (right == totalPage) {
                left = Math.max(1, left - (right - left));
            }
        }

        // 将左边的起始位置存进request域
        request.setAttribute("pageLeftNum", left);
        // 将右边的终端位置存进request域
        request.setAttribute("pageRightNum", right);
        // 跳转
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
