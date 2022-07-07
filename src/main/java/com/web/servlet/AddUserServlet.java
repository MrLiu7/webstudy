package com.web.servlet;

import com.domain.User;
import com.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 不设置编码，会导致数据添加的中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 创建user对象
        User user = new User();
        /*user.setId(0);
        user.setName(request.getParameter("name"));
        user.setSex(request.getParameter("sex"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        user.setAddress(request.getParameter("address"));
        user.setQq(request.getParameter("qq"));
        user.setEmail(request.getParameter("email"));*/

        // 使用Apache的beanUtils简化对象的创建
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // servlet 添加用户到数据库
        boolean addSuccess = new UserServiceImpl().addUser(user);

        // 如果添加成功，重定向页面 重定向到 servlet 层
        if (addSuccess) {
            //添加成功，跳转到最后一页给予展示
            response.sendRedirect(request.getContextPath() + "/findUserByPageServlet?findPage="+new UserServiceImpl().findUserByPage(null,null).getTotalPage());
        } else {
            // 添加失败,跳转到添加页面
            response.sendRedirect(request.getContextPath()+"/add.jsp");
        }
    }
}
